package com.comanda.model.recorddto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.enumerado.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

public record EstoqueMoventoListaDtoR(String produto, TipoMovimentacao tipoMovimentacao,
		@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ") @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime datamovimento,
		BigDecimal qtde, BigDecimal estoque, BigDecimal saldoanterior) {

//	public EstoqueMoventoListaDtoR(EstoqueMovimento estoqueMovimento) {
//
//		this(estoqueMovimento.getProduto().getNome(), estoqueMovimento.getTipoMovimentacao(),
//				estoqueMovimento.getDatamovimento(), estoqueMovimento.getQtde(),
//				estoqueMovimento.getProduto().getEstoque() != null
//						? estoqueMovimento.getProduto().getEstoque().getQuantidade()
//						: estoqueMovimento.getQtde(),
//				estoqueMovimento.getSaldoanterior());
//	}

}