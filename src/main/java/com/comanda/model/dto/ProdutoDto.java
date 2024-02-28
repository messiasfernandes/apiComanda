package com.comanda.model.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
@Data
public class ProdutoDto {
	private Long id;
	private String nome;
	
	private String descricao;

	private EstoqueDto estoque;

	private MarcaDto marca;
	private PrecoDto preco;

    private SubGrupoDTO subgrupo;
	private Set<ProdutoDetalheDto> produtoDetalhe = new HashSet<>();



	

	
}
