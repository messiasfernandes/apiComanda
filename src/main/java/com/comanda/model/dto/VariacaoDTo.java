package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.comanda.domain.entity.Atributo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class VariacaoDTo {
	  private Long id;
	  private String nome_variacao;
	  @JsonIgnore
      private VariacaoDTo variacao;
	///   private PrecoDto preco;
	    @JsonProperty("produto")	
	  
	    private String Produto;
	    private List<Atributo> atributos = new ArrayList<>();
}
