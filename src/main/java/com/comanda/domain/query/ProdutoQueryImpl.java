package com.comanda.domain.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import com.comanda.domain.entity.Produto;
import com.comanda.utils.ServiceFuncoes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProdutoQueryImpl extends ServiceFuncoes implements ProdutoQuery {
	@PersistenceContext
	EntityManager em;

	@Override
	public Page<Produto> buscar(String paramentro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		Predicate[] predicates = null;

		predicates = criarRestricoes(paramentro, builder, root);

		root.fetch("subgrupo", JoinType.LEFT).fetch("grupo", JoinType.LEFT);
		root.fetch("estoque", JoinType.LEFT);
		root.fetch("marca", JoinType.LEFT);
		root.fetch("preco", JoinType.LEFT);
		root.fetch("produtoDetalhe",  JoinType.LEFT).
		fetch("atributos",  JoinType.LEFT);
		 root.fetch("componentes",  JoinType.LEFT).fetch("produto", JoinType.LEFT).fetch("preco", JoinType.LEFT);
		root.fetch("subgrupo", JoinType.LEFT).fetch("grupo", JoinType.LEFT);
		criteria.select(root);

		criteria.where(predicates);

		criteria.orderBy(builder.asc(root.get("nome")));
		TypedQuery<Produto> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro));
	}

	private Long total(String paramentro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Produto> root = criteria.from(Produto.class);

		criteria.where(criarRestricoes(paramentro, builder, root));

		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Produto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(String paramentro, CriteriaBuilder builder, Root<Produto> root) {
		List<Predicate> predicates = new ArrayList<>();

		if ((!ehnumero(paramentro) && (qtdecaraceteres(paramentro) > 0))) {

			predicates.add(builder.or(builder.like(root.get("marca").get("nomeMarca"), paramentro + "%"),

					builder.like(root.get("nome"), "%" + paramentro.toUpperCase() + "%")

			));

		}

		if ((ehnumero(paramentro)) && (qtdecaraceteres(paramentro) != 13)) {
			System.out.println("pasou aqui");
			Long id = Sonumero(paramentro);
			predicates.add(builder.or(builder.equal(root.get("id"), id)));
		}

		if ((ehnumero(paramentro)) && (qtdecaraceteres(paramentro) == 13)) {
			predicates.add(builder.like(root.get("produtoDetalhe") .get("codigobarras"), paramentro + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	public boolean isTemComponente(List<Predicate> componentes) {
		return !CollectionUtils.isEmpty(componentes);
	}
}
