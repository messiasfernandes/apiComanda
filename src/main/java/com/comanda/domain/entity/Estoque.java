package com.comanda.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tab_estoque")
public class Estoque extends GeradorId {

	private static final long serialVersionUID = 1L;
	   @JsonIgnore
	 @OneToOne(fetch = FetchType.LAZY)
	    @MapsId
	    @JoinColumn(name = "produto_id")
	    private Produto produto;
	   private Integer quantidade=0;

	

}
