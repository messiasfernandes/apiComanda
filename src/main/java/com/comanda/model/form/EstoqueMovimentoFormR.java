package com.comanda.model.form;

import com.comanda.domain.enumerado.TipoMovimentacao;

public record EstoqueMovimentoFormR( 
		TipoMovimentacao tipoMovimentacao, Long idProduto,
				 Integer qtde, Integer saldoanterior) {
	
	

}
