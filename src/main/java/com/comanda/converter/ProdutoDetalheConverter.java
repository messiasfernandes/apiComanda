package com.comanda.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.ProdutoDetalhe;
import com.comanda.model.dto.ProdutoDetalheDtoLista;

@Component
public class ProdutoDetalheConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public ProdutoDetalheDtoLista toDto(ProdutoDetalhe objeto) {

		return modelMapper.map(objeto, ProdutoDetalheDtoLista.class);
	}



	public List<ProdutoDetalheDtoLista> toCollectionDto(List<ProdutoDetalhe> objetos) {
		return objetos.stream().map(this::toDto).collect(Collectors.toList());
	}

	public Page<ProdutoDetalheDtoLista> topage(Page<ProdutoDetalhe> objetos) {

		return objetos.map(obj -> toDto(obj));
	}


}
