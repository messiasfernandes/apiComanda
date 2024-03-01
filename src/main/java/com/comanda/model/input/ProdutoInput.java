package com.comanda.model.input;

import java.util.ArrayList;
import java.util.List;

import com.comanda.utils.TolowerCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
@Data
public class ProdutoInput {
	private Long id;
	@Setter(value = AccessLevel.NONE)
	@NotNull
    @NotBlank(message = "Campo  obrigatorio")
	private String nome;
	@Setter(value = AccessLevel.NONE)
	private String descricao;

	private EstoqueInput estoque;

	private MarcaInput marca;
	private PrecoInput preco;
	private SubGrupoInput subgrupo;
	public void setNome(String nome) {
		this.nome = TolowerCase.normalizarString(nome);
	}
	public void setDescricao(String descricao) {
		this.descricao = TolowerCase.normalizarString(descricao);
	}
	@Valid
	private List<produtoDetalheInput> produtoDetalhe = new ArrayList<>();
	
}
