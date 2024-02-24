package com.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.api.ProdutoContrllerOpeAapi;
import com.comanda.converter.ProdutoConverter;
import com.comanda.domain.sservice.ProdutoService;
import com.comanda.model.dto.ProdutoDto;
import com.comanda.model.input.ProdutoInput;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoContrllerOpeAapi {
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoConverter produtoConverter;

	@GetMapping
	@Override
	public ResponseEntity<Page<ProdutoDto>> listar(
			@RequestParam(value = "parametro", required = false, defaultValue = "") String parametro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Pageable page) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(produtoConverter.topage(produtoService.buscar(parametro, page)));
	}

	@PostMapping
	@Override
	public ResponseEntity<ProdutoDto> criar(@Valid @RequestBody ProdutoInput produto) {
		var produtosalvo = produtoService.salvar(produtoConverter.toEntity(produto));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoConverter.toDto(produtosalvo));
	}

	@GetMapping("{id}")
	@Override
	public ResponseEntity<ProdutoDto> buscar(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDto(produtoService.buccarporid(id)));
	}

	@PutMapping("{id}")
	@Override
	public ResponseEntity<ProdutoDto> Atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoInput produto) {

		produto.setId(id);
		var produtoEditado = produtoService.salvar(produtoConverter.toEntity(produto));
		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDto(produtoEditado));
	}

	@DeleteMapping("{id}")
	@Override
	public ResponseEntity<Void> remover( @PathVariable  Long id) {
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
