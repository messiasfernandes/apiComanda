package com.comanda.model.dto;

import lombok.Data;

@Data
public class ProdutoDtoComponete {
	private Long id;
	private String nome;
	private EstoqueDto estoque;
}
