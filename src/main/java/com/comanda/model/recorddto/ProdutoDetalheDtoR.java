package com.comanda.model.recorddto;

import java.math.BigDecimal;

import com.comanda.domain.entity.ProdutoDetalhe;
import com.comanda.domain.enumerado.UnidadeMedida;

public record ProdutoDetalheDtoR(Long id, String codigobarras, BigDecimal desconto, Integer mutiplicador,
		UnidadeMedida unidadeMedida, Integer qtdeEstoque) {
	
	public ProdutoDetalheDtoR(ProdutoDetalhe produtoDetalhe) {
		this (produtoDetalhe.getId(), produtoDetalhe.getCodigobarras(), produtoDetalhe.getDesconto(), produtoDetalhe.getMutiplicador(),
				produtoDetalhe.getUnidadeMedida(), calcularEstoqueDisponivel(produtoDetalhe));

	}

	

	private static Integer calcularEstoqueDisponivel(ProdutoDetalhe produtoDetalhe) {
		if (produtoDetalhe.getProduto().getEstoque() != null) {
			return produtoDetalhe.getProduto().getEstoque().getQuantidade().intValue() / produtoDetalhe.getMutiplicador();
		} else
			return 0;
	}

}
