package com.comanda.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ProdutoInput {
	private Long id;
	@NotNull
	 @NotBlank(message = "Campo  obrigatorio")
	private String nome;

	private String descricao;

	private EstoqueInput estoque;

	private MarcaInput marca;
}
