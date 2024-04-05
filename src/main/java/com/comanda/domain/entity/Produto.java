package com.comanda.domain.entity;

import java.util.HashSet;
import java.util.Set;

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
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	 @Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@BatchSize(size = 10)
	private Set<ProdutoDetalhe> produtoDetalhe = new HashSet<>();
	@JsonIgnoreProperties(value = { "nomeSubgrupo" }, allowGetters = true)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "subgrupo_id")
	private SubGrupo subgrupo;
	@OneToOne(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "produto_id")
	private Preco preco;
	@Column(length = 15)
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;
	@Column(length = 15)
	private String codigoFabricante;

	@Fetch(FetchMode.SUBSELECT)
	@BatchSize(size = 5)
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinTable(name = "composisao", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "componente_id"))
	private Set<Componente> componentes = new HashSet<>();

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

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", descricao=" + descricao + ", imagem=" + imagem + ", estoque=" + estoque
				+ ", marca=" + marca + ", produtoDetalhe=" + produtoDetalhe + ", subgrupo=" + subgrupo + ", preco="
				+ preco + ", tipoProduto=" + tipoProduto + ", codigoFabricante=" + codigoFabricante + ", componentes="
				+ componentes + ", qtdeEstoque=" + qtdeEstoque + "]";
	}




	
}
