package com.comanda.model.dto;

import java.math.BigDecimal;

import com.comanda.domain.enumerado.UnidadeMedida;

import jakarta.validation.constraints.Digits;
import lombok.Data;
@Data
public class ProdutoDetalheDto {
	private Long id;
	private String codigobarras;

	@Digits(integer = 9, fraction = 3)
	private BigDecimal desconto;
	private Integer mutiplicador =1;
	//@Getter(value = AccessLevel.NONE)
	private UnidadeMedida unidadeMedida;


}
