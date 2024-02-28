package com.comanda.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Variacao extends GeradorId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

//	@OneToOne(mappedBy = "variacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, 
//		       optional = true)
//	@JoinColumn(name = "variacao_id")
//	private Preco preco;
	@Transient
	   @Getter(value = AccessLevel.NONE)
	    private String nomeProduto;
	    @JsonProperty("produto")	
	    public String getNomeProduto() {
			return nomeProduto= produto.getNome();
		}
	    @Transient
	    @Getter(value = AccessLevel.NONE)
	    private BigDecimal valor;
	    @Fetch(FetchMode.SUBSELECT)
		@ElementCollection(fetch = FetchType.LAZY)
		@CollectionTable(name = "variacao_atributos", joinColumns = @JoinColumn(name = "variacao_id"))
		@BatchSize(size = 10)
		private List<Atributo> atributos = new ArrayList<>();
	    public BigDecimal getValor() {
			if(atributos.size()>0) {
			for (int i = 0; i < atributos.size(); i++) {
				valor= atributos.get(i).getCustoadiconal();
			}
			}else {
				valor= BigDecimal.ZERO;
			}
			return valor;
		}
}
