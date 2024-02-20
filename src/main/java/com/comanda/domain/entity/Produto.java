package com.comanda.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_produtos")
public class Produto extends GeradorId {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	    private String descricao;
	 
	    @OneToOne(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinColumn(name = "produto_id")
		private Estoque estoque;
	    @JsonIgnoreProperties(value = { "nomeMarca" }, allowGetters = true)
	    @ManyToOne( optional = true)
		@JoinColumn(name = "marca_id")
		private Marca marca;
		
}
