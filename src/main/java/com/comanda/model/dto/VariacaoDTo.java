package com.comanda.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class VariacaoDTo {
	  private Long id;
	  private String nome_variacao;
	  @JsonIgnore
       private ProdutoDto produto;
	    private BigDecimal valor_variacao;
	    @JsonProperty("produto")	
	  
	    private String Produto;
	   
}
