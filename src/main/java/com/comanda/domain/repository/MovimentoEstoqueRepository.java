package com.comanda.domain.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.query.EstoqueMovmentoQuery;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<EstoqueMovimento, Long>,EstoqueMovmentoQuery  {
	@Query("SELECT DISTINCT em FROM EstoqueMovimento em " + "LEFT JOIN FETCH em.produto p " )
//
//			"INNER JOIN FETCH p.estoque e " + "WHERE (p.nomeProduto LIKE %:parametro%  ) "
//			+ "AND (em.datamovimento BETWEEN :dataInicio AND :dataFim) " + "ORDER BY em.dataMovimento DESC")
	Page<EstoqueMovimento> listar(@Param("parametro") String parametro, @Param("dataInicio") LocalDate dataInicio,
		@Param("dataFim") LocalDate dataFim, Pageable pageable);
}
