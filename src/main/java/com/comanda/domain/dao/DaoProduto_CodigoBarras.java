package com.comanda.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Produto_CodigoBarras;

@Repository
public interface DaoProduto_CodigoBarras extends JpaRepository<Produto_CodigoBarras, Long> {
//	@Query(" from Produto_CodigoBarras  pc LEFT JOIN FETCH pc.produto p where p.id")
//	public Produto_CodigoBarras buscarproduto(Long id);
}
