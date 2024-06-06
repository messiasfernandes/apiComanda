package com.comanda.model.dto;

import com.comanda.domain.enumerado.Localizacao;
import com.comanda.domain.enumerado.StatusMesa;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class MesaDto {
	private Long id;
	private Integer numerodaMesa;

	private Integer capacidade;

	private Localizacao loclizacao;

	private StatusMesa statusMesa;

	public MesaDto() {
		// TODO Auto-generated constructor stub
	}

	public MesaDto(Long id, StatusMesa statusMesa, Integer numerodaMesa,Integer capacidade) {
		super();
		this.id = id;
		this.numerodaMesa = numerodaMesa;
		this.statusMesa = statusMesa;
		this.capacidade= capacidade;
	}

}
