package com.comanda.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.comanda.domain.entity.ItemComanda;
import com.comanda.domain.entity.Mesa;
import com.comanda.domain.enumerado.StatusPagamentoComanda;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComandaDTo {
	
	private Long id;

	private LocalDateTime data_abertura;
	
	private StatusPagamentoComanda statusPagamentoComanda;
	@NotNull
	private Mesa mesa;
	private BigDecimal total;
	private List<ItemdaComandaDTo> itemsdaComanda = new ArrayList<>();
}
