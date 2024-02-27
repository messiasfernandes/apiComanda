package com.comanda.model.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;
@Data
public class PrecoDto {
    private Long id;
    @NumberFormat(pattern = "###,###.00", style = Style.CURRENCY) 
	private BigDecimal precovenda;
	
	@NumberFormat(style = Style.CURRENCY) 
	private BigDecimal precocusto;

	@NumberFormat(pattern = "#,###.00",style = Style.CURRENCY) 
	private BigDecimal customedio;
  
}
