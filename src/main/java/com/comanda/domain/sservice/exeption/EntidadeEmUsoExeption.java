package com.comanda.domain.sservice.exeption;

import org.springframework.dao.DataIntegrityViolationException;

public class EntidadeEmUsoExeption extends DataIntegrityViolationException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExeption(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
