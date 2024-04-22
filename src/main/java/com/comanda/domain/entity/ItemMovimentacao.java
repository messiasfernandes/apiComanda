package com.comanda.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class ItemMovimentacao implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@Override
	public int hashCode() {
		return Objects.hash(id, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemMovimentacao other = (ItemMovimentacao) obj;
		return Objects.equals(id, other.id) && Objects.equals(produto, other.produto);
	}
	@Override
	public String toString() {
		return "ItemMovimentacao [id=" + id + ", produto=" + produto + ", estoqueMovimento=" + estoqueMovimento
				+ ", qtde=" + qtde + ", saldoanterior=" + saldoanterior + "]";
	}
	
	

}
