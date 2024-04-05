package com.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.api.ProdutoDetalheContrllerOpeAapi;
import com.comanda.converter.ProdutoDetalheConverter;
import com.comanda.domain.repository.ProdutosDetalheRepository;
import com.comanda.domain.service.ProdutoDetalheservice;
import com.comanda.model.dto.ProdutoDetalheDtoLista;

@RequestMapping("/produtosdetalhes")
@RestController
public class ProdutoDetalhecontroller extends ControllerEvent implements ProdutoDetalheContrllerOpeAapi  {
	@Autowired
	private ProdutosDetalheRepository combinacaoRepository;
	@Autowired
	private ProdutoDetalheConverter produtoDetalheConverter;
	@Autowired
    private ProdutoDetalheservice produtoDetalheservice;
	

	public ResponseEntity<List< ProdutoDetalheDtoLista> >criar() {
		// List<ProdutoDetalheDtoLista> listaProdutos = combinacaoRepository.findAll()
		        //    .stream()
		       //     .map(elemento -> new ProdutoDetalheDtoLista(elemento))
		       //     .collect(Collectors.toList());
		    return ResponseEntity.status(HttpStatus.OK).body(produtoDetalheConverter.toCollectionDto(combinacaoRepository.findAll()));
	}

  @GetMapping
	@Override                
	public ResponseEntity<Page<ProdutoDetalheDtoLista>> listar(@RequestParam(value = "parametro", required = false, defaultValue = "") String parametro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Pageable page) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.OK).body(produtoDetalheConverter.topage(produtoDetalheservice.buscar(parametro, page)));
	}

}
