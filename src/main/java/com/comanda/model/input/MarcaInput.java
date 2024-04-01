package com.comanda.model.input;

import lombok.Data;

@Data
public class MarcaInput {
	private Long id;
	private String nomeMarca;
	@Override
	public String toString() {
		return "MarcaInput [id=" + id + ", nomeMarca=" + nomeMarca + "]";
	}

}
