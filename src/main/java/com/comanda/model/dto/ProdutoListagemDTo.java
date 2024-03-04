package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.comanda.domain.enumerado.SituacaoEstoque;

import lombok.Data;

@Data
public class ProdutoListagemDTo {
    private Long id;
    private String produto;
    private BigDecimal precovenda = BigDecimal.ZERO;
    private String marca;
    private SituacaoEstoque situacao;
    private String subgrupo;
 //   @JsonIgnore
   // private PrecoDto preco;
    private List<ProdsutoDetalheLista> produtoDetalhe = new ArrayList<>();
}
