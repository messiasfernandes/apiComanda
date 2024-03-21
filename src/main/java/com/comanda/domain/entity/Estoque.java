package com.comanda.domain.entity;

import java.math.BigDecimal;

import com.comanda.domain.enumerado.ControlarEstoque;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tab_estoque")
public class Estoque {
    @Id
	private Long id;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "produto_id")
	private Produto produto;
	@Digits(integer = 9, fraction = 3)
	@Column
	private BigDecimal quantidade = BigDecimal.ZERO;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal estoqueminimo;
	@Digits(integer = 9, fraction = 3)
    private	BigDecimal estoquemaximo;
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private ControlarEstoque controlarEstoque;

}
