package com.comanda.model.form;

import java.math.BigDecimal;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.enumerado.TipoMovimentacao;

public record EstoqueMovimentoFormR( 
		TipoMovimentacao tipo, ProdutoFormR produto,
				 BigDecimal qtde, BigDecimal saldoanterior) {
	
//	public EstoqueMovimentoFormR(EstoqueMovimento estoqueMovimento) {
//		this(estoqueMovimento.getTipoMovimentacao(),new ProdutoFormR(estoqueMovimento.getProduto().getId()), 
//				estoqueMovimento.getQtde(), estoqueMovimento.getSaldoanterior());
//		
//	}

}
