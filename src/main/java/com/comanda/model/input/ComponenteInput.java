package com.comanda.model.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ComponenteInput {
    private Long id;
	private BigDecimal qtde;

	private BigDecimal subtotal;
	
	private ProdutoComponenteInput produto;
}
