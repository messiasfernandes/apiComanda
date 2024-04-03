package com.comanda.domain.entity;




import com.comanda.utils.TolowerCase;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */

@EqualsAndHashCode
@Getter
@Setter
@Embeddable

public class Atributo   {

	/**
	 * 
	 */
	
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String chave;
	@Setter(value = AccessLevel.NONE)

	@Column(length = 60)
	private String valor;

    public void setChave(String chave) {
	this.chave = TolowerCase.normalizarString(chave);
}
	public void setValor(String valor) {
		this.valor = TolowerCase.normalizarString(valor);
	}

}
