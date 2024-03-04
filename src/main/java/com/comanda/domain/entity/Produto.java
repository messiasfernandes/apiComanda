package com.comanda.domain.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_produtos")
public class Produto extends GeradorId {

	private static final long serialVersionUID = 1L;
@JsonProperty("produto")
	@Column(length = 150)
	private String nome;
	@Column(length = 250)
	private String descricao;
	@OneToOne(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private Estoque estoque;
	@JsonIgnoreProperties(value = { "nomeMarca" }, allowGetters = true)
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "marca_id")
	private Marca marca;
	// @Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@BatchSize(size = 10)
	private List<ProdutoDetalhe> produtoDetalhe = new ArrayList<>();
	@JsonIgnoreProperties(value = { "nomeSubgrupo" }, allowGetters = true)
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "subgrupo_id")
	private SubGrupo subgrupo;
	@OneToOne(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "produto_id")
	private Preco preco;


}
