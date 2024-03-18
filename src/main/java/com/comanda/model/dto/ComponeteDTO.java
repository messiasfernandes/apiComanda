package com.comanda.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ComponeteDTO {
	private Long id;
	private BigDecimal qtde;
    private BigDecimal subtotal;
	private String nome;
}
