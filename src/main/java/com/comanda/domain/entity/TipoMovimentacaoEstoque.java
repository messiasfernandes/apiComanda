package com.comanda.domain.entity;

import com.comanda.domain.enumerado.Operacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class TipoMovimentacaoEstoque  extends GeradorId{

	
	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(length = 150, nullable = false)
	private String descricao;
	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private Operacao operacao;

}
