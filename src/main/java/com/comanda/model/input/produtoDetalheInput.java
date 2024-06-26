package com.comanda.model.input;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.EAN;

import com.comanda.domain.entity.Atributo;
import com.comanda.domain.enumerado.UnidadeMedida;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class produtoDetalheInput {
	private Long id;
	@EAN
	@NotBlank
    @Size(min = 13, max = 13, message = "o campo precisar ter {min} caracteres ")
	//@Pattern(regexp = "\\d{13}")
	private String codigobarras;

	@Digits(integer = 9, fraction = 3)
	private BigDecimal desconto;
	private Integer mutiplicador =1;
	@NotNull
	@Enumerated(EnumType.STRING )
	private UnidadeMedida unidadeMedida;
	
	private Set<Atributo> atributos = new HashSet<>();
}
