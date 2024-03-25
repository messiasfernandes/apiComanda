package com.comanda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comanda.domain.entity.ProdutoDetalhe;

public interface ProdutosDetalheRepository extends JpaRepository<ProdutoDetalhe, Long> {

}
