package com.comanda.model.recorddto;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoListaDtoR(Long id, String produto, BigDecimal preco,

		String marca, String subgrupo, List<ProdutoDetalheDtoR> produtoDetalhe) {

//	public ProdutoListaDtoR(Produto produto) {
//	    this(produto.getId(), produto.getNome(), produto.getPreco().getPrecovenda(),
//	        produto.getMarca() != null ? produto.getMarca().getNomeMarca() : null,
//	        produto.getSubgrupo() != null ? produto.getSubgrupo().getNomeSubgrupo() : null,
//	   ///     converterListaProdutoDetalhe(produto.getProdutoDetalhe()));
//	}
//	private static List<ProdutoDetalheDtoR> converterListaProdutoDetalhe(List<ProdutoDetalhe> produtoDetalheList) {
//		return produtoDetalheList.stream().map(ProdutoDetalheDtoR::new).collect(Collectors.toList());
//	}
//	
}
