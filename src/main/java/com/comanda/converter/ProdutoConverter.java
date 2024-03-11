package com.comanda.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.Produto;
import com.comanda.model.dto.ProdutoDto;
import com.comanda.model.dto.ProdutoListagemDTo;
import com.comanda.model.input.ProdutoInput;
import com.comanda.model.recorddto.DetalharProdutoR;
import com.comanda.model.recorddto.ProdutoListaDtoR;

@Component
public class ProdutoConverter {
	@Autowired
	private ModelMapper modelMapper;


	public ProdutoDto toDtoDetalhe(Produto objeto) {
	
			    
			
		return modelMapper.map(objeto, ProdutoDto.class);
		// new DetalharProdutoR(objeto);
	
	}

	public ProdutoListagemDTo toDto(Produto objeto) {

		
//		modelMapper.addMappings(new PropertyMap<Produto, ProdutoListagemDTo>() {
//
//			@Override
//			protected void configure() {
//			      String meuproduto = source.getNome();
//			      map().setProduto(meuproduto);
//
//			   
//			}
//		});

		return modelMapper.map(objeto, ProdutoListagemDTo.class);
	}

	public Produto toEntity(ProdutoInput objeto) {
		return modelMapper.map(objeto, Produto.class);
	}

	public Page<ProdutoListagemDTo> topage(Page<Produto> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

	public ProdutoListaDtoR toRecDTo(Produto produto) {
		return new ProdutoListaDtoR(produto);
	}

	public Page<ProdutoListaDtoR> topageRecDto(Page<Produto> objetos) {

		return objetos.map(obj -> toRecDTo(obj));
	}

	public DetalharProdutoR toRecDto(Produto produto) {
		return new DetalharProdutoR(produto);
	}
}
