package com.comanda.model.dto;

import java.math.BigDecimal;


import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@JsonInclude(value = Include.NON_NULL)

@Data
public class PrecoDto {
	private Long id;
	@NumberFormat(pattern = "###,###.00", style = Style.CURRENCY)
	private BigDecimal precovenda;

	@NumberFormat(style = Style.CURRENCY)
	private BigDecimal precocusto;

	@NumberFormat(pattern = "#,###.00", style = Style.CURRENCY)
	private BigDecimal customedio;

	public PrecoDto() {
		// TODO Auto-generated constructor stub
	}

	public PrecoDto(BigDecimal precovenda) {
		super();
		this.precovenda = precovenda;
	}

}
