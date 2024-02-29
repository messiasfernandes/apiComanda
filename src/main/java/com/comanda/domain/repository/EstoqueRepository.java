package com.comanda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comanda.domain.entity.Estoque;



public interface EstoqueRepository  extends JpaRepository<Estoque, Long>{
	
	@Query("from Estoque e where e.produto.id  =:idproduto  ")
	Estoque buscarproduto(Long idproduto);
	

}
