package com.comanda.model.recorddto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

public record EstoqueMoventoListaDtoR(String produto, TipoMovimentacao tipoMovimentacao,
		@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ") @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime datamovimento,
		Integer qtde, Integer estoque, Integer saldoanterior) {

	public EstoqueMoventoListaDtoR(EstoqueMovimento estoqueMovimento) {
		this(estoqueMovimento.getProduto().getNome(), estoqueMovimento.getTipoMovimentacao(),
				estoqueMovimento.getDatamovimento(), estoqueMovimento.getQtde(),
				estoqueMovimento.getProduto().getEstoque().getQuantidade(), estoqueMovimento.getSaldoanterior());
	}

}