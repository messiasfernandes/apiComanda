package com.comanda.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EstoqueDto {
   private Long id;
   private BigDecimal quantidade;
}
