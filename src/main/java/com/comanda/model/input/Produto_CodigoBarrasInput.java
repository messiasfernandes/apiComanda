package com.comanda.model.input;

import java.math.BigDecimal;

import com.comanda.domain.enumerado.UnidadeMedida;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Produto_CodigoBarrasInput {
	private Long id;
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

}
