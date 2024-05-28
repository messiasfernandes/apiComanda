package com.comanda.domain.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.comanda.domain.entity.Produto;

public interface ProdutoRepositoryCustom {
	Page<Produto> listarProdutos(String parametro, Pageable pageable);

}
