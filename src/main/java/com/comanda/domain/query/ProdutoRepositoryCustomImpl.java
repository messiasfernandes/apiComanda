package com.comanda.domain.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import com.comanda.domain.entity.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class ProdutoRepositoryCustomImpl implements ProdutoRepositoryCustom {
	 @PersistenceContext
	    private EntityManager entityManager;
	@Override
	public Page<Produto> listarProdutos(String parametro, Pageable pageable) {
		 String baseQuery = "SELECT p.id FROM Produto p";
	        String countQuery = "SELECT COUNT(p) FROM Produto p";
	        String condition = "";
	        
	        if (parametro != null && !parametro.isEmpty()) {
	            condition = " WHERE p.nome LIKE :parametro";
	        }

	        String idQueryStr = baseQuery + condition + " ORDER BY p.nome";
	        String countQueryStr = countQuery + condition;

	        // Consulta para buscar IDs
	        TypedQuery<Long> idQuery = entityManager.createQuery(idQueryStr, Long.class);
	        if (parametro != null && !parametro.isEmpty()) {
	            idQuery.setParameter("parametro", "%" + parametro + "%");
	        }
	        idQuery.setFirstResult((int) pageable.getOffset());
	        idQuery.setMaxResults(pageable.getPageSize());
	        List<Long> produtoIds = idQuery.getResultList();

	        // Total de elementos para paginação
	        TypedQuery<Long> countTypedQuery = entityManager.createQuery(countQueryStr, Long.class);
	        if (parametro != null && !parametro.isEmpty()) {
	            countTypedQuery.setParameter("parametro", "%" + parametro + "%");
	        }
	        long total = countTypedQuery.getSingleResult();

	        // Consulta para buscar produtos completos pelos IDs
	        TypedQuery<Produto> produtoQuery = entityManager.createQuery(
	                "SELECT DISTINCT p FROM Produto p " +
	                "LEFT JOIN FETCH p.produtoDetalhe pc " +
	                "LEFT JOIN FETCH p.marca m " +
	                "LEFT JOIN FETCH p.subgrupo s " +
	                "LEFT JOIN FETCH p.estoque e " +
	                "LEFT JOIN FETCH p.preco pr " +
	                "LEFT JOIN FETCH s.grupo g " +
	                "LEFT JOIN FETCH p.componentes pk " +
	                "LEFT JOIN FETCH pc.atributos a "+
	                "WHERE p.id IN :ids ORDER BY p.nome", Produto.class);
	        produtoQuery.setParameter("ids", produtoIds);
	        List<Produto> produtos = produtoQuery.getResultList();

	        return new PageImpl<>(produtos, pageable, total);
	    }
}
