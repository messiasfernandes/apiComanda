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
	private ProdutosRepository produtoRepository;

	@Autowired
	private ProdutoConverter produtoConverter;

	@Transactional
	@Override
	public Page<Produto> buscar(String nome, Pageable pageable) {

		Page<Produto> page = null;
		if (!ehnumero(nome) && (qtdecaraceteres(nome) >= 0)) {

			nome = TolowerCase.normalizarString(nome);
			page = produtoRepository.Listar(nome, pageable);

		}
		if ((ehnumero(nome)) && (qtdecaraceteres(nome) != 13)) {
			Long id = Sonumero(nome);
			System.out.println("id" + id);
			page = produtoRepository.buscarporId(id, pageable);
		}
		if ((ehnumero(nome)) && (qtdecaraceteres(nome) == 13)) {
			page = produtoRepository.buscarPorEan(nome, pageable);
		}

		return page;
	}

	@Transactional
	@Override
	public void excluir(Long codigo) {
		try {
			buccarporid(codigo);
			produtoRepository.deleteById(codigo);
			produtoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					"Operação não permitida!! Este registro pode estar asssociado a outra tabela");
		}

	}

	@Override
	public Produto buccarporid(Long id) {

		var produto = produtoRepository.findProdutoWithDetalhesAndComponentes(id).orElseThrow(() -> new RegistroNaoEncontrado("Produto não encontrado"));

		return produto;
	}

	@Transactional()
	public Produto Alterar(ProdutoInput objeto) {
		System.out.println("produtoinput"+objeto);
		var produtoEditado = produtoRepository.getReferenceById(objeto.getId());
		produtoEditado = produtoConverter.toEntity(objeto);
		
		if (!objeto.getProdutoDetalhe().isEmpty()) {
			System.out.println(objeto.getProdutoDetalhe().size());
			produtoEditado.getProdutoDetalhe().forEach(p -> p.setProduto(produtoConverter.toEntity(objeto)));
		}
		if (produtoEditado.getPreco() != null) {

			produtoEditado.getPreco().setProduto(produtoEditado);
		}
		if (!produtoEditado.getComponentes().isEmpty()) {

			produtoEditado.getComponentes().forEach(c -> c.getProduto().setPreco(c.getProduto().getPreco()));
			produtoEditado.getComponentes().forEach(c -> c.setProduto(c.getProduto()));

		}

		return produtoRepository.save(produtoEditado);
		// produtoRepository.save(produtoRepository.findById(id).map( p->
		// produtoConverter.toEntity(objeto) ).get());
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
			if (!objeto.getComponentes().isEmpty()) {

				objeto.getComponentes().forEach(c -> c.setProduto(c.getProduto()));
			}
		} catch (NegocioException e) {
			throw new NegocioException("Erro ao persistir os dados");
		}

		return produtoRepository.save(objeto);
	}

	 
}
