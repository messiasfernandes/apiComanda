package com.comanda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.converter.ProdutoConverter;
import com.comanda.domain.entity.Produto;
import com.comanda.domain.repository.ProdutosRepository;
import com.comanda.domain.service.exeption.EntidadeEmUsoExeption;
import com.comanda.domain.service.exeption.NegocioException;
import com.comanda.domain.service.exeption.RegistroNaoEncontrado;
import com.comanda.model.input.ProdutoInput;
import com.comanda.utils.ServiceFuncoes;
import com.comanda.utils.TolowerCase;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService extends ServiceFuncoes implements ServiceModel<Produto> {
    @Autowired
    private ProdutosRepository daoProduto;
    @Autowired
    private ProdutoConverter produtoConverter;


    @Override
    public Page<Produto> buscar(String nome, Pageable pageable) {
        System.out.println("passou" + nome);
        Page<Produto> page = null;
        if (!ehnumero(nome) && (qtdecaraceteres(nome) >= 0)) {
            System.out.println("nome" + nome);
            nome = TolowerCase.normalizarString(nome);
            page = daoProduto.Listar(nome, pageable);
        }
        if ((ehnumero(nome)) && (qtdecaraceteres(nome) != 13)) {
            Long id = Sonumero(nome);
            System.out.println("id" + id);
            page = daoProduto.buscarporId(id, pageable);
        }
        if ((ehnumero(nome)) && (qtdecaraceteres(nome) == 13)) {
            page = daoProduto.buscarPorEan(nome, pageable);
        }

        return page;
    }

    @Transactional
    @Override
    public void excluir(Long codigo) {
        try {
            buccarporid(codigo);
            daoProduto.deleteById(codigo);
            daoProduto.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExeption(
                    "Operação não permitida!! Este registro pode estar asssociado a outra tabela");
        }

    }

    @Override
    public Produto buccarporid(Long id) {
    
        return daoProduto.findById(id).orElseThrow(() -> new RegistroNaoEncontrado("Produto não encontrado"));
    }
    @Transactional()
    public Produto Alterar(ProdutoInput objeto) {
        var produtoEditado = daoProduto.getReferenceById(objeto.getId());
        produtoEditado = produtoConverter.toEntity(objeto);
        if (!objeto.getProdutoDetalhe().isEmpty()) {
            System.out.println(objeto.getProdutoDetalhe().size());
            produtoEditado.getProdutoDetalhe().forEach(p -> p.setProduto(produtoConverter.toEntity(objeto)));
        }
        if (produtoEditado.getPreco() != null) {

            produtoEditado.getPreco().setProduto(produtoEditado);
        }

        return daoProduto.save(produtoEditado);
        //daoProduto.save(daoProduto.findById(id).map( p-> produtoConverter.toEntity(objeto) ).get());
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public Produto salvar(Produto objeto) {
        try {
            if (objeto.getPreco() != null) {

                objeto.getPreco().setProduto(objeto);
            }

            if (!objeto.getProdutoDetalhe().isEmpty()) {

                objeto.getProdutoDetalhe().forEach(p -> p.setProduto(objeto));

            }
        } catch (NegocioException e) {
            throw new NegocioException("Erro ao persistir os dados");
        }

        return daoProduto.save(objeto);
    }

}
