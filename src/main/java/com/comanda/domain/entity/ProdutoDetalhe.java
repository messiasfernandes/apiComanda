package com.comanda.domain.entity;

import java.math.BigDecimal;

import com.comanda.domain.enumerado.UnidadeMedida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	//@JsonIgnore
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

	public Integer getQtdePorUnidade() {
		if (produto.getEstoque() != null) {

			return qtdePorUnidade = produto.getEstoque().getQuantidade().intValue() / mutiplicador;
		} else {
			return qtdePorUnidade;
		}

	}
}
