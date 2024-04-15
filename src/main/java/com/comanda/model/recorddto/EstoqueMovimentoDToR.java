package com.comanda.model.recorddto;

import java.math.BigDecimal;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.enumerado.TipoMovimentacao;

public record EstoqueMovimentoDToR(TipoMovimentacao tipoMovimentacao, 
		 BigDecimal qtde, BigDecimal saldoanterior) {
	
//	public EstoqueMovimentoDToR(EstoqueMovimento estoqueMovimento) {
//		this(estoqueMovimento.getTipoMovimentacao(), estoqueMovimento.getQtde(), estoqueMovimento.getSaldoanterior());
//	}

}
