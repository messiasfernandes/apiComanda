package com.comanda.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Componente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	private List<Produto> produtos = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(id, produto);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Componente that = (Componente) o;
		if (id == null || that.id == null) {
			return super.equals(o); // Fallback para a implementação padrão de equals
		}
		return Objects.equals(id, that.id);
	}

	@Override
	public String toString() {
		return "Componente [id=" + id + ", qtde=" + qtde + ", subtotal=" + subtotal + ", produto=" + produto
				+ ", produtos=" + produtos + "]";
	}


}
