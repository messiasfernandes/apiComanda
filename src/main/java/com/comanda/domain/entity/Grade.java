package com.comanda.domain.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@NamedEntityGraph(name = "Grade.caracteristicas", attributeNodes = @NamedAttributeNode("caracteristicas"))
public class Grade extends GeradorId {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Column(length = 100)
	private String descricao;
	@Digits(integer = 9, fraction = 3)
	@Column
	private BigDecimal quantidade = BigDecimal.ZERO;
	
	//@JsonBackReference
	
	 @Fetch(FetchMode.SUBSELECT)
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "grade", cascade = CascadeType.ALL, orphanRemoval = true)
		@BatchSize(size = 10)
	private Set<Caracteristica> caracteristicas = new HashSet<>();
}
