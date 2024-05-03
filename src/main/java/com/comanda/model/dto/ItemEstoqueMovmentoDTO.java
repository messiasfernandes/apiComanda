package com.comanda.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
public class ItemEstoqueMovmentoDTO {
   private ProdutoDtoComponete produto;
   @Digits(integer = 9, fraction = 3)
	private BigDecimal qtde;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal  saldoanterior;
	
}
