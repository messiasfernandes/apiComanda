package com.comanda.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.comanda.domain.enumerado.TipoMovimentacao;
import com.comanda.model.form.EstoqueMovimentoFormR;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "tab_estoque_movimentacao")
public class EstoqueMovimento extends GeradorId{


	private static final long serialVersionUID = 1L;
	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	//@Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
    private Produto produto;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal qtde;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal  saldoanterior;
	public EstoqueMovimento() {
		// TODO Auto-generated constructor stub
	}
   public EstoqueMovimento(EstoqueMovimentoFormR estoqueMovimentoFormR) {
	
	  this.tipoMovimentacao= estoqueMovimentoFormR.tipo();
	  this.produto =new Produto();
	  this.produto.setId(estoqueMovimentoFormR.produto().id());
	   this.qtde= estoqueMovimentoFormR.qtde();
}  
}
