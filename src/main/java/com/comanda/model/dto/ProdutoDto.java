package com.comanda.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.comanda.domain.enumerado.TipoProduto;

import lombok.Data;
@Data
public class ProdutoDto {
	private Long id;
	private String nome;
	
	private String descricao;

	private EstoqueDto estoque;

	private MarcaDto marca;
	private PrecoDto preco;

    private SubGrupoDTO subgrupo;
	private String imagem;
	private TipoProduto tipoProduto;
	private String codigoFabricante;
	private List<ProdutoDetalheDto> produtoDetalhe = new ArrayList<>();

	private Set<ComponeteDTO> componentes = new HashSet<>();

	

	
}
