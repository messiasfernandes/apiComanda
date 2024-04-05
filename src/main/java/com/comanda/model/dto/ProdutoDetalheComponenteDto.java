package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.comanda.domain.entity.Atributo;
import com.comanda.domain.enumerado.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import lombok.Data;
@JsonInclude(Include.NON_EMPTY)
@Data
public class ProdutoDetalheComponenteDto {
	private Long id;
	private String codigobarras;

	@Digits(integer = 9, fraction = 3)
	private BigDecimal desconto;
	private Integer mutiplicador =1;
	//@Getter(value = AccessLevel.NONE)
	@Transient
	
	private UnidadeMedida unidadeMedida;
	 private Set<Atributo> atributos = new HashSet<>();
}
