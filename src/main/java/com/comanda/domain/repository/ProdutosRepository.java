package com.comanda.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Produto;
import com.comanda.domain.query.ProdutoQuery;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long>, ProdutoQuery {
	@EntityGraph(attributePaths = { "preco","produtoDetalhe","produtoDetalhe.atributos",
			"marca","componentes","estoque"  }, type = EntityGraphType.FETCH)
	@Query(value = "SELECT DISTINCT p FROM Produto p " + "LEFT JOIN FETCH p.marca m " 	
			+ "LEFT JOIN FETCH p.subgrupo s "
       + "LEFT JOIN FETCH p.subgrupo.grupo sg " 
			+ "WHERE p.nome LIKE %:parametro% OR m.nomeMarca LIKE %:parametro%  OR s.nomeSubgrupo LIKE %:parametro% "
			+ "OR sg.nomeGrupo LIKE %:parametro% " + "ORDER BY  p.nome " ,
			countQuery = "Select count(p) from Produto p")
	Page<Produto> Listar(@Param("parametro") String parametro, Pageable pageable);
	
	
	@EntityGraph(attributePaths = {"produtoDetalhe.atributos", "preco",
			"marca","componentes","estoque" , "subgrupo" }, type = EntityGraphType.FETCH)
	@Query(value = "SELECT DISTINCT p FROM Produto p " 
			 + "LEFT JOIN FETCH p.produtoDetalhe pc "
			 + "LEFT JOIN FETCH  pc.atributos a "
			+ "WHERE  pc.codigobarras = :parametro" ,
			countQuery = "Select count(p) from Produto p")
	Page<Produto> buscarPorEan(@Param("parametro") String parametro, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Produto p " + "LEFT JOIN FETCH p.marca m " + "LEFT JOIN FETCH p.estoque e "
			+ "LEFT JOIN FETCH p.preco pe " + "LEFT JOIN FETCH p.produtoDetalhe pc " + "LEFT JOIN FETCH p.subgrupo s "
			+"LEFT JOIN FETCH s.grupo sg "
			+"LEFT JOIN FETCH pc.atributos a "
			+	"LEFT JOIN FETCH p.componentes c "
			+	"LEFT JOIN FETCH c.produto pco "
			+	"LEFT JOIN FETCH pco.estoque ec "
			+	"LEFT JOIN FETCH pco.marca pm "
			+	"LEFT JOIN FETCH pco.preco pp "
			+	"LEFT JOIN FETCH pco.subgrupo pcs "
			+	"LEFT JOIN FETCH pcs.grupo pcg "
			+ "WHERE     p.id =:parametro")
	Page<Produto> buscarporId(Long parametro, Pageable pageable);

	@Query(value = "SELECT DISTINCT p FROM Produto p " 
	+"LEFT JOIN FETCH p.marca m " 
			+ "LEFT JOIN FETCH p.estoque e "
			+ "LEFT JOIN FETCH p.preco pe " 
	+ "LEFT JOIN FETCH p.produtoDetalhe pc "
			+ "LEFT JOIN FETCH p.subgrupo s "
			+ "LEFT JOIN FETCH s.grupo sg "
			+"LEFT JOIN FETCH pc.atributos a "
			+	"LEFT JOIN FETCH p.componentes c "
			+	"LEFT JOIN FETCH c.produto pco "
			+	"LEFT JOIN FETCH pco.estoque ec "
			+	"LEFT JOIN FETCH pco.preco pp "
			+	"LEFT JOIN FETCH pco.marca pm "
			+	"LEFT JOIN FETCH pco.subgrupo pcs "
			+	"LEFT JOIN FETCH pcs.grupo pcg "
			+ "WHERE p.id =:id")
	Optional<Produto> findProdutoWithDetalhesAndComponentes(Long id);

}
