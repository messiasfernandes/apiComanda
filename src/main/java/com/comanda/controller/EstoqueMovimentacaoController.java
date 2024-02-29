package com.comanda.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.entity.EstoqueMovimento;

@RequestMapping("/movimentacoesestoque")
@RestController
public class EstoqueMovimentacaoController {
	@Autowired
	private com.comanda.domain.service.ServiceEstoqueMovimento serviceEstoqueMovimento;

	@GetMapping
	public ResponseEntity<Page<EstoqueMovimento>> listar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "tipo", required = false, defaultValue = "") String tipo,
			@RequestParam(value = "dataincio", required = false) LocalDate dataincio,
			@RequestParam(value = "datafim", required = false) LocalDate datafim,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(serviceEstoqueMovimento.listar(paramentro, tipo, dataincio, datafim, page));
	}

}
