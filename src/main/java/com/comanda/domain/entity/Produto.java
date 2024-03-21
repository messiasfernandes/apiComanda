package com.comanda.domain.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.comanda.domain.enumerado.TipoProduto;
import com.comanda.utils.TolowerCase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_produtos")
public class Produto extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 150)
	private String nome;
	@Column(length = 250)
	private String descricao;
	@Column(length = 250)
	private String imagem;
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
	@Column(length = 15)
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;
	@Column(length = 15)
	private String codigoFabricante;
	@Fetch(FetchMode.SUBSELECT)
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "produto_componente", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "componente_id"))
	private List<Componente> componentes;

	@Getter(value = AccessLevel.NONE)
	@Transient
	private Integer qtdeEstoque;

	public Integer getQtdeEstoque() {
		if (estoque != null) {
			qtdeEstoque = estoque.getQuantidade().intValue();
		} else {
			qtdeEstoque = 0;
		}
		return qtdeEstoque;
	}

	public void setNome(String nome) {
		this.nome = TolowerCase.normalizarString(nome);
	}
}
