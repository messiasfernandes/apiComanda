package com.comanda.domain.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.comanda.domain.enumerado.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_produtoDetalhe")
public class ProdutoDetalhe extends GeradorId {

	private static final long serialVersionUID = 1L;
	
	@Column(length = 13)
	private String codigobarras;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal desconto;
	private Integer mutiplicador = 1;
	@Enumerated(EnumType.STRING)
	private UnidadeMedida unidadeMedida;
	@Transient
	@Getter(value = AccessLevel.NONE)
	private Integer qtdePorUnidade = 0;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "produtoDetalhe", cascade = CascadeType.ALL, orphanRemoval = true)
	@BatchSize(size = 10)
	private Set<Atributo> atributos = new HashSet<>();
	public Integer getQtdePorUnidade() {
		if (produto.getEstoque() != null) {

			return qtdePorUnidade = produto.getEstoque().getQuantidade().intValue() / mutiplicador;
		} else {
			return qtdePorUnidade;
		}

	}
}
