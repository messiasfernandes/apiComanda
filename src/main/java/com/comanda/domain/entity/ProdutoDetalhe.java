package com.comanda.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.comanda.domain.enumerado.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
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
public class ProdutoDetalhe implements Serializable  {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "produtoDetalhe", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "produto_atributos", joinColumns = @JoinColumn(name = "produtodetalhe_id"))
	@OrderColumn
	@BatchSize(size = 10)
	
	private Set<Atributo> atributos = new HashSet<>();
	public Integer getQtdePorUnidade() {
		if (produto.getEstoque() != null) {

			return qtdePorUnidade = produto.getEstoque().getQuantidade().intValue() / mutiplicador;
		} else {
			return qtdePorUnidade;
		}

	}
	@Override
	public int hashCode() {
		return Objects.hash(codigobarras, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoDetalhe other = (ProdutoDetalhe) obj;
		return Objects.equals(codigobarras, other.codigobarras) && Objects.equals(id, other.id);
	}
	

}
