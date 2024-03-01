package com.comanda.model.recorddto;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.enumerado.TipoMovimentacao;

public record EstoqueMovimentoDToR(TipoMovimentacao tipoMovimentacao,
		@DateTimeFormat(pattern = " dd/MM/yyyy ") Integer qtde, Integer saldoanterior) {
	
	public EstoqueMovimentoDToR(EstoqueMovimento estoqueMovimento) {
		this(estoqueMovimento.getTipoMovimentacao(), estoqueMovimento.getQtde(), estoqueMovimento.getSaldoanterior());
	}

}
