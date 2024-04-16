package com.comanda.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.enumerado.Operacao;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EstoqueMovimentoDTo {
	private Long id;
	private Operacao operacao;
	private TipoMovimentacao tipoMovimentacao;
	// @Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;
	private Set<ItemEstoqueMovmentoDTO> items = new HashSet<>();

}
