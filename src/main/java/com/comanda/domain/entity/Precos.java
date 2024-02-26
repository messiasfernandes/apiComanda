package com.comanda.domain.entity;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "precos")
public class Precos extends GeradorId{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  @ManyToOne
	    @JoinColumn(name = "id_produto")
	    private Produto produto;

	    @ManyToOne
	    @JoinColumn(name = "id_variacao")
	    private Variacao variacao;

	    private BigDecimal preco;

	    private LocalDate data_inicio;

	    private LocalDate data_fim;

}
