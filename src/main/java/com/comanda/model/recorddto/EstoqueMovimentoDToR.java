package com.comanda.model.recorddto;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.enumerado.TipoMovimentacao;

public record EstoqueMovimentoDToR(TipoMovimentacao tipoMovimentacao, 
		 Integer qtde, Integer saldoanterior) {
	
	public EstoqueMovimentoDToR(EstoqueMovimento estoqueMovimento) {
		this(estoqueMovimento.getTipoMovimentacao(), estoqueMovimento.getQtde(), estoqueMovimento.getSaldoanterior());
	}

}
