package com.comanda.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class ProdutoComponenteDTo {
	  private Long id;
	    private String nome;
	   // private EstoqueDto estoque;

		private MarcaDto marca;


	    private SubGrupoDTO subgrupo;
	   
	     private PrecoDto preco;
	     
	    private List<ProdutoDetalheComponenteDto> produtoDetalhe = new ArrayList<>();
		//private Set<ComponeteDTO> componentes;
	   
	    private Set<ComponeteDTO> componentes = new HashSet<>();

}
