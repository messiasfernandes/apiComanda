package com.comanda.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.comanda.domain.enumerado.SituacaoEstoque;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@JsonInclude(Include.NON_EMPTY)
@Data
public class ProdutoListagemDTo {
    private Long id;
    private String nome;
   // private EstoqueDto estoque;

	private String marca;


    private String subgrupo;
    private SituacaoEstoque situacao;
     private PrecoDto preco;
     
    private List<ProdutoDetalheLista> produtoDetalhe = new ArrayList<>();
	//private Set<ComponeteDTO> componentes;
   
    private Set<ComponeteDTO> componentes = new HashSet<>();
    
}
