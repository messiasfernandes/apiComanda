package com.comanda.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_EMPTY)
@Data
public class ProdutoDetalhado {

	private Long id;
	private String nome;
	private PrecoDto preco;
	private MarcaDto marca;

	public ProdutoDetalhado() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoDetalhado(Long id, String nome, PrecoDto preco) {
		
		this.id = id;
		this.nome = nome;
		this.preco =preco;
	}

	private Set<ProdutoDetalheComponenteDto> componentes = new HashSet<>();

}
