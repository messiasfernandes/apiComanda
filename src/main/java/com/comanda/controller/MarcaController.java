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

import com.comanda.api.ControllerMarcaOpenApi;
import com.comanda.converter.MarcaProdutoConverter;
import com.comanda.domain.service.MarcaService;
import com.comanda.model.dto.MarcaDto;
import com.comanda.model.input.MarcaInput;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/marcas")
public class MarcaController implements ControllerMarcaOpenApi {
	@Autowired
	private MarcaService serviceMarcaProduto;
	@Autowired
	private MarcaProdutoConverter marcaProdutoConverter;

	@GetMapping
	@Override
	public ResponseEntity<Page<MarcaDto>> listar(
			@RequestParam(value = "parametro", required = false, defaultValue = "") String parametro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Pageable page) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(marcaProdutoConverter.topage(serviceMarcaProduto.buscar(parametro, page)));
	}


	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		serviceMarcaProduto.excluir(id);
		return ResponseEntity.noContent().build();
	}
    @PutMapping("/{id}")
	@Override
	public ResponseEntity<MarcaDto> Atualizar(@PathVariable Long id, @Valid @RequestBody MarcaInput marca, HttpServletResponse response) {
    	
    	System.out.println(marca.getNomeMarca());
    	marca.setId(id);
		var marcaSalva = serviceMarcaProduto.salvar(marcaProdutoConverter.toEntity(marca));
		return ResponseEntity.status(HttpStatus.OK).body(marcaProdutoConverter.toDto(marcaSalva));
	}

	@PostMapping
	@Override
	public ResponseEntity<MarcaDto> criar(@Valid @RequestBody MarcaInput marca,
			HttpServletResponse response) {
		var marcaSalva = serviceMarcaProduto.salvar(marcaProdutoConverter.toEntity(marca));
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaProdutoConverter.toDto(marcaSalva));
	}


	@Override
	public ResponseEntity<MarcaDto> buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	

	


}
