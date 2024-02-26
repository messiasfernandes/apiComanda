package com.comanda.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tab_precos")
public class Preco  {

	
//	private static final long serialVersionUID = 1L;
	@Id
    private Long id;
	@Digits(integer = 9, fraction = 3)
	//@Setter(value = AccessLevel.NONE)
	private BigDecimal precovenda;
	//@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 3)
	private BigDecimal precocusto;

	@Digits(integer = 9, fraction = 3)
	private BigDecimal customedio;
	//@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@Getter(value = AccessLevel.NONE)
    @Transient
    private BigDecimal precoVaricao;
	public BigDecimal getPrecoVaricao() {
		if(produto.getVariacoes().size()>0) {
			for (int i=0; i<produto.getVariacoes().size();i++) {
				precoVaricao= produto.getPreco().getPrecovenda().add( produto.getVariacoes().get(i).getValor_variacao());
			}
		}
		return precoVaricao;
	}
}
