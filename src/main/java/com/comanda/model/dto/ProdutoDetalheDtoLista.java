package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.comanda.domain.entity.Atributo;
import com.comanda.domain.enumerado.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import lombok.Data;
@JsonInclude(Include.NON_EMPTY)
@Data
public class ProdutoDetalheDtoLista {
	private Long id;
    private ProdutoDetalhado produto ;
   
	private String codigobarras;
	//@Getter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 3)
	
	private BigDecimal desconto;
	private Integer mutiplicador =1;
	//@Getter(value = AccessLevel.NONE)
	@Transient
	private Integer qtdePorUnidade=0;
	private UnidadeMedida unidadeMedida;
	 private Set<Atributo> atributos = new HashSet<>();
	public ProdutoDetalheDtoLista(Long id, String codigobarras,
			@Digits(integer = 9, fraction = 3) BigDecimal desconto,ProdutoDetalhado produto  ) {
	
		this.id = id;
	
		this.codigobarras = codigobarras;
		this.desconto = desconto;
		this.produto= produto;
	}
	 public BigDecimal getDesconto() {
		 if(desconto== null) {
			 desconto= BigDecimal.ZERO;
		 }
		return desconto;
	}
}
