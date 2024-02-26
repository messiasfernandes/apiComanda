package com.comanda.model.input;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class VariacaoInput {
	
	  private Long id;
	  private String nome_variacao;

	    private BigDecimal valor_variacao;
}
