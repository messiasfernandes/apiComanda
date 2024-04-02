package com.comanda.domain.entity;




import java.io.Serializable;
import java.math.BigDecimal;

import com.comanda.utils.TolowerCase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Atributo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String chave;
	@Setter(value = AccessLevel.NONE)

	@Column(length = 60)
	private String valor;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
   private ProdutoDetalhe produtoDetalhe;
    public void setChave(String chave) {
	this.chave = TolowerCase.normalizarString(chave);
}
	public void setValor(String valor) {
		this.valor = TolowerCase.normalizarString(valor);
	}

}
