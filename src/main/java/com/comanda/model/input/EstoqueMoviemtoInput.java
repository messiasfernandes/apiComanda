package com.comanda.model.input;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.entity.ItemMovimentacao;
import com.comanda.domain.entity.TipoMovimentacaoEstoque;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EstoqueMoviemtoInput {
	private Long id;
	
	
	//@Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;
	private TipoMovimentacaoEstoque tipoMovimentacaoEstoque;
	private Set<ItemMovimentacao> items = new HashSet<>();

	
}
