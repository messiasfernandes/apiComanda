package com.comanda.domain.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.comanda.domain.entity.Produto;



public interface ProdutoQuery {
	Page<Produto> buscar(String paramentro, Pageable pageable);
	

}
