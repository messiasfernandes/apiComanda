package com.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.entity.Mesa;
import com.comanda.domain.repository.MesasRepository;
@CrossOrigin
@RestController
@RequestMapping("/mesas")
public class MesaController {
	@Autowired
	private MesasRepository mesasRepository;
	@GetMapping
	public ResponseEntity<List< Mesa> >buscar(){
		return ResponseEntity.status(HttpStatus.OK).body(mesasRepository.buscarMesas());
	}

}
