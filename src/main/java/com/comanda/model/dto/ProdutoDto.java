package com.comanda.model.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProdutoDto {
	private Long id;
	private String nome;
	
	private String descricao;

	private EstoqueDto estoque;

	private MarcaDto marca;
	private BigDecimal precovenda;
	private PrecoDto preco;
}
