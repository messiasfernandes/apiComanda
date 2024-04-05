package com.comanda.model.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.entity.Produto;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
public class EstoqueMoviemtoInput {
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	//@Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;

    private ProdutoInput produto;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal qtde;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal  saldoanterior;
}
