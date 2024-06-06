package com.comanda.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.comanda.domain.enumerado.StatusPagamentoComanda;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ComandaDTo {

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data_abertura;
	private BigDecimal total;
	private StatusPagamentoComanda statusPagamentoComanda;

	private MesaDto mesa;

	private List<ItemdaComandaDTo> itemsdaComanda = new ArrayList<>();


	public ComandaDTo() {
		// TODO Auto-generated constructor stub
	}

	public ComandaDTo(Long id, LocalDateTime data_abertura, StatusPagamentoComanda statusPagamentoComanda, MesaDto mesa,
			BigDecimal total) {
		this.id = id;
		this.data_abertura = data_abertura;
		this.statusPagamentoComanda = statusPagamentoComanda;
		this.mesa = mesa;
		this.total = total;
	}
}
