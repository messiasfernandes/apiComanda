package com.comanda.model.recorddto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.comanda.domain.entity.Produto;
import com.comanda.domain.entity.ProdutoDetalhe;

public record ProdutoListaDtoR(Long id, String produto, BigDecimal preco,

		String marca, String subgrupo, List<ProdutoDetalheDtoR> produtoDetalhe) {

	public ProdutoListaDtoR(Produto produto) {
	    this(produto.getId(), produto.getNome(), produto.getPreco().getPrecovenda(),
	        produto.getMarca() != null ? produto.getMarca().getNomeMarca() : null,
	        produto.getSubgrupo() != null ? produto.getSubgrupo().getNomeSubgrupo() : null,
	        converterListaProdutoDetalhe(produto.getProdutoDetalhe()));
	}
	private static List<ProdutoDetalheDtoR> converterListaProdutoDetalhe(List<ProdutoDetalhe> produtoDetalheList) {
		return produtoDetalheList.stream().map(ProdutoDetalheDtoR::new).collect(Collectors.toList());
	}
	
}
