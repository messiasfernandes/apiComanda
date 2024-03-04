package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class ProdutoListagemDTo {
    private Long id;
    private String produto;
    private BigDecimal precovenda;
    private String nomeMarca;
    @JsonAlias("subgrupo")
    private String nomeSubgrupo;
  
    private List<ProdsutoDetalheLista> produtoDetalhe = new ArrayList<>();
}
