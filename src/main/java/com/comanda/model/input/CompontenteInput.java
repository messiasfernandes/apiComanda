package com.comanda.model.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CompontenteInput {
    private ProdutoComponenteInput produto;
    
    private BigDecimal qtde;

	private BigDecimal subtotal;

	@Override
	public String toString() {
		return "CompontenteInput [produto=" + produto + ", qtde=" + qtde + ", subtotal=" + subtotal + "]";
	}
}
