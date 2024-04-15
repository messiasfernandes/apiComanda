package com.comanda.domain.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class ItemMovimentacao extends GeradorId {

	
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
    private Produto produto;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private EstoqueMovimento estoqueMovimento;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal qtde;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal  saldoanterior;

}
