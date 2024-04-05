package com.comanda.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comanda.domain.entity.ProdutoDetalhe;

public interface ProdutosDetalheRepository extends JpaRepository<ProdutoDetalhe, Long> {

	@Query(value = "SELECT DISTINCT pd FROM ProdutoDetalhe pd "
	        + "LEFT JOIN FETCH pd.produto p "
	        + "LEFT JOIN FETCH p.marca m "
	        + "LEFT JOIN FETCH p.estoque e "
	        + "LEFT JOIN FETCH p.preco pe "
	        + "LEFT JOIN FETCH p.subgrupo s "
	        + "LEFT JOIN FETCH s.grupo sg "
	        + "LEFT JOIN FETCH pd.atributos a "
	        + "LEFT JOIN FETCH p.componentes c "
	        + "LEFT JOIN FETCH c.produto pco "
	        + "LEFT JOIN FETCH pco.estoque ec "
	        + "LEFT JOIN FETCH pco.marca pm "
	        + "LEFT JOIN FETCH pco.preco pp "
	        + "LEFT JOIN FETCH pco.subgrupo pcs "
	        + "LEFT JOIN FETCH pcs.grupo pcg "
	        + "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro% OR s.nomeSubgrupo LIKE %:parametro% "
	        + "OR sg.nomeGrupo LIKE %:parametro% ORDER BY p.nome")
	Page<ProdutoDetalhe> listar(@Param("parametro") String parametro, Pageable pageable);
	
	@Query(value = "SELECT DISTINCT pd FROM ProdutoDetalhe pd "
	        + "LEFT JOIN FETCH pd.produto p "
	        + "LEFT JOIN FETCH p.marca m "
	        + "LEFT JOIN FETCH p.estoque e "
	        + "LEFT JOIN FETCH p.preco pe "
	        + "LEFT JOIN FETCH p.subgrupo s "
	        + "LEFT JOIN FETCH s.grupo sg "
	        + "LEFT JOIN FETCH pd.atributos a "
	        + "LEFT JOIN FETCH p.componentes c "
	        + "LEFT JOIN FETCH c.produto pco "
	        + "LEFT JOIN FETCH pco.estoque ec "
	        + "LEFT JOIN FETCH pco.marca pm "
	        + "LEFT JOIN FETCH pco.preco pp "
	        + "LEFT JOIN FETCH pco.subgrupo pcs "
	        + "LEFT JOIN FETCH pcs.grupo pcg "
			+ "WHERE     pd.codigobarras = :parametro")
	Page<ProdutoDetalhe> buscarPorEan(@Param("parametro") String parametro, Pageable pageable);

	
}
