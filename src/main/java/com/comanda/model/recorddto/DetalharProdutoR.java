package com.comanda.model.recorddto;

import java.util.List;
import java.util.stream.Collectors;

import com.comanda.domain.entity.Produto;
import com.comanda.domain.entity.ProdutoDetalhe;

public record DetalharProdutoR(Long id, String nome, String descricao, DetalharMarcaR marca,
		DetalharSubrgupoR subugrupo, DetalharprecoR preco, List<ProdutoDetalheDtoR> produtoDetalhe

) {
//	public DetalharProdutoR(Produto produto) {
//		this(produto.getId(), produto.getNome(), produto.getDescricao(),
//				produto.getMarca() != null
//						? new DetalharMarcaR(produto.getMarca().getId(), produto.getMarca().getNomeMarca())
//						: null,
//				produto.getSubgrupo() != null
//						? new DetalharSubrgupoR(produto.getSubgrupo().getId(), produto.getSubgrupo().getNomeSubgrupo(),
//								produto.getSubgrupo().getGrupo() != null
//										? new DetalharGrupoR(produto.getSubgrupo().getGrupo().getId(),
//												produto.getSubgrupo().getGrupo().getNomeGrupo())
//										: null)
//						: null,
//				produto.getPreco() != null
//						? new DetalharprecoR(produto.getPreco().getId(), produto.getPreco().getPrecovenda(),
//								produto.getPreco().getPrecocusto(), produto.getPreco().getCustomedio())
//						: null,
//				converterListaProdutoDetalhe(produto.getProdutoDetalhe()));
//
//	}

	private static List<ProdutoDetalheDtoR> converterListaProdutoDetalhe(List<ProdutoDetalhe> produtoDetalhe) {
		return produtoDetalhe.stream().map(ProdutoDetalheDtoR::new).collect(Collectors.toList());
	}

}
