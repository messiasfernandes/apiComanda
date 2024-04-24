package com.comanda.model.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ProdutoDtoComponete {
	private Long id;
	private String nome;
	private EstoqueDto estoque;
	 /// private Set<ComponeteDTO> componentes = new HashSet<>();
	
}
