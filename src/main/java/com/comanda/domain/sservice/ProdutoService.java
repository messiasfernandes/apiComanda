package com.comanda.domain.sservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.dao.DaoProduto;
import com.comanda.domain.entity.Produto;
import com.comanda.domain.sservice.exeption.EntidadeEmUsoExeption;
import com.comanda.domain.sservice.exeption.NegocioException;
import com.comanda.domain.sservice.exeption.RegistroNaoEncontrado;
import com.comanda.utils.ServiceFuncoes;
import com.comanda.utils.TolowerCase;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService extends ServiceFuncoes implements ServiceModel<Produto> {
	@Autowired
	private DaoProduto daoProduto;


	@Override
	public Page<Produto> buscar(String nome, Pageable pageable) {
		Page<Produto> page = null;
		if (!ehnumero(nome) && (qtdecaraceteres(nome) >= 0)) {
			nome = TolowerCase.normalizarString(nome);
			page = daoProduto.Listar(nome, pageable);
		}
		if ((ehnumero(nome)) && (qtdecaraceteres(nome) != 13)) {
			Long id = Sonumero(nome);
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
//		if (daoProduto.findById(id).isEmpty()) {
//			
//			daoProduto.findById(id).orElseThrow(()->new RegistroNaoEncontrado("Produto não encotrado"));
//			
//		}
		return daoProduto.findById(id).orElseThrow(()->new RegistroNaoEncontrado("Produto não encotrado"));
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public Produto salvar(Produto objeto) {
		try {
//			if (objeto.getPreco() != null) {
//
//				objeto.getPreco().setProduto(objeto);
//			}
//
//			if (objeto.getProdutos_codigo().size() > 0) {
//
//				objeto.getProdutos_codigo().forEach(p -> p.setProduto(objeto));
//
//			}
		} catch (NegocioException e) {
			throw new NegocioException("Erro ao persistir os dados");
		}

		return daoProduto.save(objeto);
	}

}
