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
import org.springframework.web.util.UriComponentsBuilder;

import com.comanda.api.ProdutoContrllerOpeAapi;
import com.comanda.converter.ProdutoConverter;
import com.comanda.domain.entity.Produto;
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
	public ResponseEntity<ProdutoDto> criar( @RequestBody @Valid ProdutoInput produto, UriComponentsBuilder uri) {
		var produtosalvo = produtoService.salvar(produtoConverter.toEntity(produto));
		var url = uri.path("/produtuos/{id}").buildAndExpand(produtosalvo.getId()).toUri();
		return ResponseEntity. created(url).body(produtoConverter.toDto(produtosalvo));
	}

	@GetMapping("{id}")
	@Override
	public ResponseEntity<ProdutoDto> buscar(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDto(produtoService.buccarporid(id)));
	}

	@PutMapping
	@Override
	public ResponseEntity<ProdutoDto> Atualizar( @Valid @RequestBody ProdutoInput produto) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDto(produtoService.Alterar(produto)));
	}

	@DeleteMapping("{id}")
	@Override
	public ResponseEntity<Void> remover( @PathVariable  Long id) {
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
