package com.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.dao.DaoProduto;
import com.comanda.domain.entity.Produto;

@RestController
@RequestMapping(path = "produtos")
public class ProdutoController {
	@Autowired
	private DaoProduto daoProduto;
	@GetMapping
    public  ResponseEntity<java.util.List<Produto>> listar(){
    	return ResponseEntity.status(HttpStatus.OK).body(daoProduto.findAll());
    }
}
