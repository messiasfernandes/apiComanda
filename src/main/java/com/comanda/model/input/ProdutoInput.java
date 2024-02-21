package com.comanda.model.input;

import lombok.Data;
@Data
public class ProdutoInput {
	private Long id;
	private String nome;
	
	private String descricao;

	private EstoqueInput estoque;

	private MarcaInput marca;
}
