package com.comanda.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.comanda.domain.entity.Mesa;
import com.comanda.domain.enumerado.StatusPagamentoComanda;

import lombok.Data;

@Data
public class ComandaDTo {

	private Long id;

	private LocalDateTime data_abertura;

	private StatusPagamentoComanda statusPagamentoComanda;

	private Mesa mesa;
	private BigDecimal total;
	private List<ItemdaComandaDTo> itemsdaComanda = new ArrayList<>();

//	public ComandaDTo(Long id, LocalDateTime data_abertura, StatusPagamentoComanda statusPagamentoComanda,
//			@NotNull Mesa mesa, BigDecimal total, List<ItemdaComandaDTo> itemsdaComanda) {
//		
//		this.id = id;
//		this.data_abertura = data_abertura;
//		this.statusPagamentoComanda = statusPagamentoComanda;
//		this.mesa = mesa;
//		this.total = total;
//		this.itemsdaComanda = itemsdaComanda;
//	}

	public ComandaDTo(StatusPagamentoComanda statusPagamentoComanda, Mesa mesa, BigDecimal total) {
		this.statusPagamentoComanda = statusPagamentoComanda;
		this.mesa = mesa;
		this.total = total;
	}
}
