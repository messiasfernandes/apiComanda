package com.comanda.domain.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Produto;
@Repository
public interface DaoProduto extends JpaRepository<Produto, Long> {
	@Query(value = "SELECT DISTINCT p FROM Produto p " +
	           "LEFT JOIN FETCH p.marca m " +
	           "LEFT JOIN FETCH p.estoque e " +
	           "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro% " +
	           "ORDER BY p.nome",
	       countQuery = "SELECT COUNT(DISTINCT p.id) FROM Produto p " +
	                    "LEFT JOIN p.marca m " +
	                    "LEFT JOIN p.estoque e " +
	                    "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro%")
	Page<Produto> Listar(@Param("parametro") String parametro, Pageable pageable);

//	@Query(value = "SELECT DISTINCT p FROM Produto p " +
//	           "LEFT JOIN FETCH p.marca m " +
//	           "LEFT JOIN FETCH p.estoque e " +
//	           "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro% " +
//	           "ORDER BY p.nome",
//	       countQuery = "SELECT COUNT(DISTINCT p.id) FROM Produto p " +
//	                    "LEFT JOIN p.marca m " +
//	                    "LEFT JOIN p.estoque e " +
//	                    "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro%")
//	Page<Produto> Listar(@Param("parametro") String parametro, Pageable pageable);

}
