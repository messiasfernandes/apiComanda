package com.comanda.domain.enumerado;

public enum TipodeMovimentacaoEstoque {
	teste("teste de sofwores");

	public String getNovo() {
		return novo;
	}

	public void setNovo(String novo) {
		this.novo = novo;
	}

	private String novo;

	private TipodeMovimentacaoEstoque(String novo) {
		this.novo = novo;
	}

}
