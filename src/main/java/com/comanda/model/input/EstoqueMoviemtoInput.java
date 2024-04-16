package com.comanda.model.input;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.entity.ItemMovimentacao;
import com.comanda.domain.enumerado.Operacao;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EstoqueMoviemtoInput {
	private Long id;
	
	private Operacao operacao;
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	//@Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;

	private List<ItemMovimentacao> items = new ArrayList<>();
}
