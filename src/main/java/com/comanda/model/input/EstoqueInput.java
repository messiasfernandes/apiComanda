package com.comanda.model.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EstoqueInput {
   private Long id;
   private BigDecimal quantidade = BigDecimal.ZERO;
}
