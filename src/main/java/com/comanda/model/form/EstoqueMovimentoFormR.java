package com.comanda.model.form;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.entity.Produto;
import com.comanda.domain.enumerado.TipoMovimentacao;

public record EstoqueMovimentoFormR( 
		TipoMovimentacao tipo, ProdutoFormR produto,
				 Integer qtde, Integer saldoanterior) {
	
	public EstoqueMovimentoFormR(EstoqueMovimento estoqueMovimento) {
		this(estoqueMovimento.getTipoMovimentacao(),new ProdutoFormR(estoqueMovimento.getProduto().getId()), 
				estoqueMovimento.getQtde(), estoqueMovimento.getSaldoanterior());
		
	}

}
