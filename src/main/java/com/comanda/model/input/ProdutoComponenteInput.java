/**
 * 
 */
package com.comanda.model.input;

import lombok.Data;

@Data
public class ProdutoComponenteInput {
	private Long id;
	private String nome;
	private PrecoInput preco;
//	private	MarcaInput marca;
//	private SubGrupoInput subgurpo;
	@Override
	public String toString() {
		return "ProdutoComponenteInput [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
	}


}
