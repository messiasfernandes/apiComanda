package com.comanda.domain.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_produtos")
public class Produto extends GeradorId {

	private static final long serialVersionUID = 1L;

	@Column(length = 120)
	private String nome;
	@Column(length = 250)
	private String descricao;
	@OneToOne(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private Estoque estoque;
	@JsonIgnoreProperties(value = { "nomeMarca" }, allowGetters = true)
	@ManyToOne(fetch = FetchType.LAZY ,optional = true)
	@JoinColumn(name = "marca_id")
	private Marca marca;
	@OneToOne(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private Preco preco;
	@Fetch(FetchMode.SUBSELECT)
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@BatchSize(size = 10)
	private Set<Produto_CodigoBarras> produtos_codigo = new HashSet<>();
}
