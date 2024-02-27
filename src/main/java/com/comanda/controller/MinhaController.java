package com.comanda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.entity.MinhaClasse;

import jakarta.validation.Valid;

@RequestMapping("meuend")
@RestController
public class MinhaController {
	@PostMapping
	public ResponseEntity<MinhaClasse> criar(@RequestBody @Valid MinhaClasse minhaClasse) {
		return ResponseEntity.status(HttpStatus.CREATED).body(minhaClasse);
	}

}
