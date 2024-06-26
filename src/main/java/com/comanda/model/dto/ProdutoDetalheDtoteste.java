package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.comanda.domain.entity.Atributo;
import com.comanda.domain.enumerado.UnidadeMedida;
import com.comanda.model.input.ProdutoComponenteInput;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProdutoDetalheDtoteste {
  private ProdutoComponenteInput produto = new ProdutoComponenteInput();
	private Long id;
	private String codigobarras;

	@Digits(integer = 9, fraction = 3)
	private BigDecimal desconto;
	private Integer mutiplicador =1;
	//@Getter(value = AccessLevel.NONE)
	@Transient
	private Integer qtdePorUnidade=0;
	private UnidadeMedida unidadeMedida;
	 private Set<Atributo> atributos = new HashSet<>();

	 
}
