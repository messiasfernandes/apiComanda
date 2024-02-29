package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProdutoListagemDTo {
    private Long id;
    private String nome;
    private BigDecimal precovenda;
    private String nomeMarca;
    private String nomeSubgrupo;
    private List<ProdsutoDetalheLista> produtoDetalhe = new ArrayList<>();
}
