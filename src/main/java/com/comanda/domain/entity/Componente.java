package com.comanda.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Componente extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Digits(integer = 6, fraction = 3)
	@NotNull
	@Column
	private BigDecimal qtde;
	@Column
	private BigDecimal subtotal;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Produto produto;
	
	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "componentes")
	private List<Produto> produtos;
}
