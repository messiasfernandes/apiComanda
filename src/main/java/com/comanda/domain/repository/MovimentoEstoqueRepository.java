package com.comanda.domain.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.enumerado.Operacao;
import com.comanda.domain.query.EstoqueMovmentoQuery;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<EstoqueMovimento, Long>, EstoqueMovmentoQuery {
	

	@Query(value = "SELECT DISTINCT em FROM EstoqueMovimento em " + "LEFT JOIN FETCH em.items i "
			+ "LEFT JOIN FETCH em.tipoMovimentacaoEstoque t " 
			+ "LEFT JOIN FETCH i.produto p " 
			+ "LEFT JOIN FETCH p.estoque e " 
			+ "LEFT JOIN FETCH p.marca m  " 
			+ "LEFT JOIN FETCH p.preco pr " 
			+ "LEFT JOIN FETCH p.subgrupo s "  
			+ "LEFT JOIN FETCH s.grupo g  " 
		    + "WHERE (p.nome LIKE %:parametro% ) " 
			+ "AND (t.operacao = :tipo) " + "AND (CAST(em.datamovimento as Date) BETWEEN :dataInicio AND :dataFim) " + 																									
			"ORDER BY em.datamovimento DESC", countQuery = "SELECT count(em) FROM EstoqueMovimento em" )
	
	Page<EstoqueMovimento> listar(@Param("parametro") String parametro, @Param("tipo") Operacao tipo,
			@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim,
			Pageable pageable);
	
	@EntityGraph(attributePaths = {"items","items.produto", "items.produto.produtoDetalhe", "items.produto.componentes", 
			"items.produto.estoque"}, type = EntityGraphType.FETCH)
	@Query("SELECT e FROM EstoqueMovimento e  inner JOIN  e.items i "
			+ "LEFT JOIN FETCH e.tipoMovimentacaoEstoque t " 
			+ " where i.produto.nome LIKE %:parametro% or t.operacao = :tipo  ")
     Page<EstoqueMovimento> pesquisar (@Param("parametro") String parametro, @Param("tipo") Operacao tipo,
  	Pageable pageable);
	
}