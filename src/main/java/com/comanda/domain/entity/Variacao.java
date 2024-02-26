package com.comanda.domain.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "variacoes")
public class Variacao extends GeradorId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	private String nome_variacao;

	private BigDecimal valor_variacao;
	@Transient
	   @Getter(value = AccessLevel.NONE)
	    private String nomeProduto;
	@JsonProperty("produto")	
	    public String getNomeProduto() {
			return nomeProduto= produto.getNome();
		}
	// Outros atributos e métodos
}
