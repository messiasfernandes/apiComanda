package com.comanda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.entity.Combinacao;
import com.comanda.domain.entity.ProdutoDetalhe;
import com.comanda.domain.repository.CombinacaoRepository;
import com.comanda.domain.repository.ProdutosDetalheRepository;
import com.comanda.model.dto.ProdutoDetalheDtoteste;
import com.comanda.model.recorddto.ProdutoDetalheDtoR;

@RequestMapping("meuend")
@RestController
public class MinhaController {
	@Autowired
	private ProdutosDetalheRepository combinacaoRepository;
	@GetMapping
	public ResponseEntity<List< ProdutoDetalheDtoteste> >criar() {
		 List<ProdutoDetalheDtoteste> listaProdutos = combinacaoRepository.findAll()
		            .stream()
		            .map(elemento -> new ProdutoDetalheDtoteste(elemento))
		            .collect(Collectors.toList());
		    return ResponseEntity.status(HttpStatus.OK).body(listaProdutos);
	}

}
