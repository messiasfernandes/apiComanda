package com.comanda.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comanda.domain.entity.Produto;

public interface DaoProduto extends JpaRepository<Produto, Long> {

}
