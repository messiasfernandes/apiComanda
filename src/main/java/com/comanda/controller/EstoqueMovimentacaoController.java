package com.comanda.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.api.EstoqueMovimentocontrollerOpenApi;
import com.comanda.converter.EstoqueMovimemtoConvereter;
import com.comanda.domain.enumerado.ControlarEstoque;
import com.comanda.domain.enumerado.Operacao;
import com.comanda.domain.service.EstoqueMovimentoService;
import com.comanda.model.dto.EstoqueMovimentoDTo;
import com.comanda.model.input.EstoqueMoviemtoInput;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequestMapping("/movimentacoesestoque")
@RestController
public class EstoqueMovimentacaoController extends ControllerEvent implements EstoqueMovimentocontrollerOpenApi {
	@Autowired
	private EstoqueMovimentoService serviceEstoqueMovimento;
	@Autowired
	private EstoqueMovimemtoConvereter estoquemovimentoConverte;

//
	@GetMapping
	@Override
	public ResponseEntity<Page<EstoqueMovimentoDTo>> listar(
			@RequestParam(value = "parametro", required = false, defaultValue = "") String parametro,
			@RequestParam(value = "tipo", required = false) Operacao tipoOperacao,
			@RequestParam(value = "datainicio", required = false) LocalDate datainicio,
			@RequestParam(value = "datafim", required = false) LocalDate datafim,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Pageable page) {
		System.out.println(size);
		System.out.println(pagina);
		return ResponseEntity.status(HttpStatus.OK).body(estoquemovimentoConverte
				.topage(serviceEstoqueMovimento.listar(parametro, tipoOperacao, datainicio, datafim, page)));
	}

//	@PostMapping

//	public ResponseEntity<EstoqueMoventoListaDtoR> criar(@RequestBody @Valid EstoqueMovimentoFormR estoqueMovimentoFormR,
//			HttpServletResponse response) {
//		var estoquesalvo = serviceEstoqueMovimento.salvar(estoquemovimentoConverte.toEntity(estoqueMovimentoFormR));
//		criaevento(estoquesalvo.getId(), response);
//		return ResponseEntity.status(HttpStatus.CREATED).body(estoquemovimentoConverte.toDto(estoquesalvo));
//	}

	@PostMapping
	@Override
	public ResponseEntity<EstoqueMovimentoDTo> adicionar(@RequestBody @Valid EstoqueMoviemtoInput movimentacoes,
			ControlarEstoque controlarEstoque, HttpServletResponse response) {
		System.out.println(movimentacoes.getItems().size());
		var movimtentoSalvo = serviceEstoqueMovimento.salvar(estoquemovimentoConverte.paraEntidy(movimentacoes));
		criaevento(movimentacoes.getId(), response);
		return ResponseEntity.status(HttpStatus.CREATED).body(estoquemovimentoConverte.toDto(movimtentoSalvo));
	}

	@GetMapping("{id}")
	@Override
	public ResponseEntity<EstoqueMovimentoDTo> detallhar(@PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(estoquemovimentoConverte.toDto(serviceEstoqueMovimento.buccarporid(id)));
	}
}
