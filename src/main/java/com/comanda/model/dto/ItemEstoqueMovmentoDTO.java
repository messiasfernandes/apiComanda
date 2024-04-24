package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
