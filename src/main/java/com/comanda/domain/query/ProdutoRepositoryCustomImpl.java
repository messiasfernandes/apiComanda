package com.comanda.domain.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import com.comanda.domain.entity.Produto;
import com.comanda.utils.ServiceFuncoes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class ProdutoRepositoryCustomImpl extends ServiceFuncoes implements ProdutoRepositoryCustom {
	 @PersistenceContext
	    private EntityManager entityManager;
		@Override
		public Page<Produto> listarProdutos(String parametro, Pageable pageable) {
		    boolean isNumeric = parametro != null && ehnumero(parametro);
		    boolean isEAN = isNumeric && qtdecaraceteres(parametro) == 13;
		    boolean isId = isNumeric && qtdecaraceteres(parametro) != 13;

		    List<Long> produtoIds = fetchProdutoIds(parametro, pageable, isNumeric, isEAN, isId);
		    long total = countProdutos(parametro, isNumeric, isEAN, isId);
		    List<Produto> produtos = fetchProdutos(produtoIds, parametro, isEAN, isId);

		    return new PageImpl<>(produtos, pageable, total);
		}

		private List<Long> fetchProdutoIds(String parametro, Pageable pageable, boolean isNumeric, boolean isEAN, boolean isId) {
		    String baseQuery = "SELECT p.id FROM Produto p";
		    String condition = getCondition(parametro, isNumeric, isEAN, isId);

		    String idQueryStr = baseQuery + condition + " ORDER BY p.nome";
		    TypedQuery<Long> idQuery = entityManager.createQuery(idQueryStr, Long.class);

		    setParameter(idQuery, parametro, isNumeric, isEAN, isId);

		    idQuery.setFirstResult((int) pageable.getOffset());
		    idQuery.setMaxResults(pageable.getPageSize());

		    return idQuery.getResultList();
		}

		private long countProdutos(String parametro, boolean isNumeric, boolean isEAN, boolean isId) {
		    String countQuery = "SELECT COUNT(p) FROM Produto p";
		    String condition = getCondition(parametro, isNumeric, isEAN, isId);

		    String countQueryStr = countQuery + condition;
		    TypedQuery<Long> countTypedQuery = entityManager.createQuery(countQueryStr, Long.class);

		    setParameter(countTypedQuery, parametro, isNumeric, isEAN, isId);

		    return countTypedQuery.getSingleResult();
		}

		private List<Produto> fetchProdutos(List<Long> produtoIds, String parametro, boolean isEAN, boolean isId) {
		    TypedQuery<Produto> produtoQuery = entityManager.createQuery(
		        "SELECT DISTINCT p FROM Produto p " +
		        "LEFT JOIN FETCH p.produtoDetalhe pc " +
		        "LEFT JOIN FETCH p.marca m " +
		        "LEFT JOIN FETCH p.subgrupo s " +
		        "LEFT JOIN FETCH p.estoque e " +
		        "LEFT JOIN FETCH p.preco pr " +
		        "LEFT JOIN FETCH s.grupo g " +
		        "LEFT JOIN FETCH p.componentes pk " +
		        "LEFT JOIN FETCH pc.atributos a " +
		        "WHERE p.id IN :ids " + (isEAN ? "AND pc.codigobarras = :parametro" : "") +
		        (isId ? "AND p.id = :parametro" : "") +
		        " ORDER BY p.nome", Produto.class);

		    produtoQuery.setParameter("ids", produtoIds);
		    if (isEAN || isId) {
		        produtoQuery.setParameter("parametro", isEAN ? parametro : Long.valueOf(parametro));
		    }

		    return produtoQuery.getResultList();
		}

		private String getCondition(String parametro, boolean isNumeric, boolean isEAN, boolean isId) {
		    if (parametro != null && !parametro.isEmpty()) {
		        if (!isNumeric && qtdecaraceteres(parametro) > 0) {
		            return " WHERE p.nome LIKE :parametro OR p.marca.nomeMarca LIKE :parametro OR p.subgrupo.nomeSubgrupo LIKE :parametro OR p.subgrupo.grupo.nomeGrupo LIKE :parametro";
		        }
		        if (isEAN) {
		            return " LEFT JOIN p.produtoDetalhe pd WHERE pd.codigobarras = :parametro";
		        }
		        if (isId) {
		            return " WHERE p.id = :id";
		        }
		    }
		    return "";
		}

		private void setParameter(TypedQuery<?> query, String parametro, boolean isNumeric, boolean isEAN, boolean isId) {
		    if (parametro != null && !parametro.isEmpty()) {
		        if (isEAN || isId) {
		            query.setParameter(isEAN ? "parametro" : "id", isEAN ? parametro : Long.valueOf(parametro));
		        } else {
		            query.setParameter("parametro", "%" + parametro + "%");
		        }
		    }
		}

	}
