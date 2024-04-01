package com.comanda.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
public class Combinacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Digits(integer = 9, fraction = 3)
	@Column
	private BigDecimal quantidade = BigDecimal.ZERO;
	
	 @Fetch(FetchMode.SUBSELECT)
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "combinacao" ,cascade = CascadeType.ALL, orphanRemoval = true)
		@BatchSize(size = 10)
	private Set<Variacao> variacoes = new HashSet<>();

	

	 
}
