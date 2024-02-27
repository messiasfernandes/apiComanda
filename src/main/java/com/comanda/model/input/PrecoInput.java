package com.comanda.model.input;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class PrecoInput {
    private Long id;
	private BigDecimal precovenda;
//	@NumberFormat(pattern = "#,###.00") 
	private BigDecimal precocusto;
	private BigDecimal customedio;
}
