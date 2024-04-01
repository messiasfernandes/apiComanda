package com.comanda.domain.entity;




import java.math.BigDecimal;

import com.comanda.utils.TolowerCase;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */
@Getter
@Setter
@Embeddable
public class Atributo {

	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String chave;
	@Setter(value = AccessLevel.NONE)

	@Column(length = 60)
	private String valor;
//	@Digits(integer = 9, fraction = 3)
//    private BigDecimal custoadiconal;
 public void setChave(String chave) {
	this.chave = TolowerCase.normalizarString(chave);
}
	public void setValor(String valor) {
		this.valor = TolowerCase.normalizarString(valor);
	}

}
