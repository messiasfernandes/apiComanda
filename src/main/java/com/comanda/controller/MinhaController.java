package com.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.entity.Combinacao;
import com.comanda.domain.entity.MinhaClasse;
import com.comanda.domain.repository.CombinacaoRepository;

import jakarta.validation.Valid;

@RequestMapping("meuend")
@RestController
public class MinhaController {
	@Autowired
	private CombinacaoRepository combinacaoRepository;
	@GetMapping
	public ResponseEntity<List< Combinacao> >criar() {
		return ResponseEntity.status(HttpStatus.OK).body(combinacaoRepository.findAll());
	}

}
