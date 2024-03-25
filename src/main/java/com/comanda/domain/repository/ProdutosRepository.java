package com.comanda.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Produto;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long> {

	@Query(value = "SELECT DISTINCT p FROM Produto p " + "LEFT JOIN FETCH p.marca m " + "LEFT JOIN FETCH p.estoque e "
			+ "LEFT JOIN FETCH p.preco pe " + "LEFT JOIN FETCH p.produtoDetalhe pc " + "LEFT JOIN FETCH p.subgrupo s "
			+ "LEFT JOIN FETCH p.subgrupo.grupo sg "
	//	+ "LEFT JOIN FETCH  p.componentes c "
			+ "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro%  OR s.nomeSubgrupo LIKE %:parametro% "
			+ "OR sg.nomeGrupo LIKE %:parametro% " + "ORDER BY  p.nome")
//	       countQuery = "SELECT COUNT(DISTINCT p.id) FROM Produto p " +
//	                    "LEFT JOIN  p.marca m " +
//	    		         "LEfT JOIN p.preco pe"
//+	                    "LEFT JOIN p.estoque e " +
//	                    "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro%")
	Page<Produto> Listar(@Param("parametro") String parametro, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Produto p " + "LEFT JOIN FETCH p.marca m " + "LEFT JOIN FETCH p.estoque e "
			+ "LEFT JOIN FETCH p.preco pe " + "LEFT JOIN FETCH p.produtoDetalhe pc " + "LEFT JOIN FETCH p.subgrupo s "
		//	+ "LEFT JOIN FETCH p.componentes c "
			+ "LEFT JOIN FETCH p.subgrupo.grupo sg " + "WHERE pc.codigobarras = :parametro")
	Page<Produto> buscarPorEan(@Param("parametro") String parametro, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Produto p " + "LEFT JOIN FETCH p.marca m " + "LEFT JOIN FETCH p.estoque e "
			+ "LEFT JOIN FETCH p.preco pe " + "LEFT JOIN FETCH p.produtoDetalhe pc " + "LEFT JOIN FETCH p.subgrupo s "
		//	+ "LEFT JOIN FETCH p.componentes c "
			+ "LEFT JOIN FETCH p.subgrupo.grupo sg " + "WHERE p.id =:parametro")
	Page<Produto> buscarporId(Long parametro, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Produto p " + "LEFT JOIN FETCH p.marca m " + "LEFT JOIN FETCH p.estoque e "
			+ "LEFT JOIN FETCH p.preco pe " + "LEFT JOIN FETCH p.produtoDetalhe pc " + "LEFT JOIN FETCH p.subgrupo s "
	//	+ "LEFT JOIN FETCH p.componentes c "
			+ "LEFT JOIN FETCH p.subgrupo.grupo sg " + "WHERE p.id =:id")
	Optional<Produto> findId(Long id);
}
