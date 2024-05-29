package com.comanda.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
public class ItemdaComandaDTo {

	private ProdutoDetalheDtoLista produtoDetalhe;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal quantidade;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal subtotal;
}
