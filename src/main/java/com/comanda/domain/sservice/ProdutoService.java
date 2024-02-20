package com.comanda.domain.sservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.dao.DaoProduto;
import com.comanda.domain.entity.Produto;
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
		
		return page;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Produto buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
  @Transactional(rollbackOn = Exception.class)
	@Override
	public Produto salvar(Produto objeto) {
	  Produto produto=null;
		 try {
		produto=	daoProduto.save(objeto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return produto;
	}

}
