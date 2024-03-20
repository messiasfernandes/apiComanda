package com.comanda.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.comanda.domain.enumerado.SituacaoEstoque;

import lombok.Data;

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
}
