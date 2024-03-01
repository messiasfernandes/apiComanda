package com.comanda.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.converter.EstoqueMovimemtoConvereter;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.comanda.domain.service.EstoqueMovimentoService;
import com.comanda.model.dto.ProdutoDto;
import com.comanda.model.form.EstoqueMovimentoFormR;
import com.comanda.model.input.ProdutoInput;
import com.comanda.model.recorddto.EstoqueMoventoListaDtoR;
import com.comanda.model.recorddto.EstoqueMovimentoDToR;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequestMapping("/movimentacoesestoque")
@RestController
public class EstoqueMovimentacaoController  extends ControllerEvent{
	@Autowired
	private EstoqueMovimentoService serviceEstoqueMovimento;
	@Autowired
	private EstoqueMovimemtoConvereter estoquemovimentoConverte;

	@GetMapping
	public ResponseEntity<Page<EstoqueMoventoListaDtoR>> listar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "tipo", required = false, defaultValue = "") TipoMovimentacao tipo,
			@RequestParam(value = "dataincio", required = false) LocalDate dataincio,
			@RequestParam(value = "datafim", required = false) LocalDate datafim,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(estoquemovimentoConverte
				.topage(serviceEstoqueMovimento.listar(paramentro, tipo, dataincio, datafim, page)));
	}

	@PostMapping

	public ResponseEntity<EstoqueMoventoListaDtoR> criar(@RequestBody @Valid EstoqueMovimentoFormR estoqueMovimentoFormR,
			HttpServletResponse response) {
		var estoquesalvo = serviceEstoqueMovimento.salvar(estoquemovimentoConverte.toEntity(estoqueMovimentoFormR));
		criaevento(estoquesalvo.getId(), response);
		return ResponseEntity.status(HttpStatus.CREATED).body(estoquemovimentoConverte.toDto(estoquesalvo));
	}
}
