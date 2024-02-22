package com.comanda.model.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;
@Data
public class PrecoDto {

	private BigDecimal precovenda;
	
	@NumberFormat(pattern = "#,###.00") 
	private BigDecimal precocusto;


	private BigDecimal customedio;
}
