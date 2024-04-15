package com.comanda.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.entity.ItemMovimentacao;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EstoqueMovimentoDTo {
	private TipoMovimentacao tipoMovimentacao;
	// @Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;
	private List<ItemEstoqueMovmentoDTO> items = new ArrayList<>();

}
