package com.comanda.model.input;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
@Data
public class PrecoInput {
    private Long id;
    @Digits(integer = 9, fraction = 3)
	@Setter(value = AccessLevel.NONE)
	private BigDecimal precovenda;
//	@NumberFormat(pattern = "#,###.00") 
    @Digits(integer = 9, fraction = 3)
  	@Setter(value = AccessLevel.NONE)
	private BigDecimal precocusto;
    @Digits(integer = 9, fraction = 3)
  	@Setter(value = AccessLevel.NONE)
	private BigDecimal customedio;
	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto.setScale(3, RoundingMode.HALF_UP);

	}

	public void setCustomedio(BigDecimal customedio) {
		this.customedio = customedio.setScale(3, RoundingMode.HALF_UP);

	}

	public void setPrecovenda(BigDecimal precovenda) {
		this.precovenda = precovenda.setScale(3, RoundingMode.HALF_UP);

	}
}
