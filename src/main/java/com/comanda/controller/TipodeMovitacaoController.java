package com.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.entity.TipoMovimentacaoEstoque;
import com.comanda.domain.repository.TiposdeMovimentaceosRepository;

@CrossOrigin
@RestController
@RequestMapping("/tipodemovimentacoes")
public class TipodeMovitacaoController {
	@Autowired
	private TiposdeMovimentaceosRepository tiposdeMovimentaceosRepository;
	@GetMapping
	public ResponseEntity<List<TipoMovimentacaoEstoque>>listar(){
		return ResponseEntity.status(HttpStatus.OK).body(tiposdeMovimentaceosRepository.findAll());
	}

}
