package com.comanda.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ComponeteDTO {
	private Long id;
	private BigDecimal qtde;
	private BigDecimal subtotal;
	private ProdutoDtoEditar produto;

}
