package com.comanda.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.enumerado.Operacao;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.comanda.model.form.EstoqueMovimentoFormR;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_estoque_movimentacao")
public class EstoqueMovimento extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private Operacao operacao;
	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	// @Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estoqueMovimento", cascade = CascadeType.ALL, orphanRemoval = true)
	@BatchSize(size = 10)
	private List<ItemMovimentacao> items = new ArrayList<>();

 
}
