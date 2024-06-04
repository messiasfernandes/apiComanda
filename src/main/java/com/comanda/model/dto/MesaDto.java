package com.comanda.model.dto;

import com.comanda.domain.enumerado.Localizacao;
import com.comanda.domain.enumerado.StatusMesa;

import lombok.Data;

@Data
public class MesaDto {
	private Long id;
	private Integer numerodaMesa;

	private Integer capacidade;

	private Localizacao loclizacao;
	
	private StatusMesa statusMesa;

}
