package com.comanda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.entity.ProdutoDetalhe;
import com.comanda.domain.repository.ProdutosDetalheRepository;
import com.comanda.utils.ServiceFuncoes;
import com.comanda.utils.TolowerCase;

@Service
public class ProdutoDetalheservice extends ServiceFuncoes implements ServiceModel<ProdutoDetalhe> {
	@Autowired
	private ProdutosDetalheRepository produtosDetalheRepository;

	@Override
	public Page<ProdutoDetalhe> buscar(String nome, Pageable pageable) {
		Page<ProdutoDetalhe> page = null;
		if (!ehnumero(nome) && (qtdecaraceteres(nome) >= 0)) {

			nome = TolowerCase.normalizarString(nome);
			page = produtosDetalheRepository.listar(nome, pageable);

		}
		if ((ehnumero(nome)) && (qtdecaraceteres(nome) == 13)) {
			page = produtosDetalheRepository.buscarPorEan(nome, pageable);
		}
		
		return page;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProdutoDetalhe buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoDetalhe salvar(ProdutoDetalhe objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
