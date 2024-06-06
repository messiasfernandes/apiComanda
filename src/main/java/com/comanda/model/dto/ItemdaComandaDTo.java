package com.comanda.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Digits;
import lombok.Data;
@JsonInclude(value = Include.NON_EMPTY)
@Data
public class ItemdaComandaDTo {

	private ProdutoDetalheDtoLista produtoDetalhe;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal quantidade;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal subtotal;

	public ItemdaComandaDTo(
			@Digits(integer = 9, fraction = 3) BigDecimal quantidade,
			@Digits(integer = 9, fraction = 3) BigDecimal subtotal ,ProdutoDetalheDtoLista produtoDetalhe ) {
		super();
		
		this.quantidade = quantidade;
		this.subtotal = subtotal;
		this.produtoDetalhe = produtoDetalhe;
	}
	
	
	
	
}
