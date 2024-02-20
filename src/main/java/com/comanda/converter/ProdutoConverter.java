package com.comanda.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.Produto;
import com.comanda.model.dto.ProdutoDto;
import com.comanda.model.input.ProdutoInput;

@Component
public class ProdutoConverter {
	@Autowired
	private ModelMapper modelMapper;

	public ProdutoDto toDto(Produto objeto) {

		return modelMapper.map(objeto, ProdutoDto.class);
	}

	public Produto toEntity(ProdutoInput objeto) {
		return modelMapper.map(objeto, Produto.class);
	}

	public Page<ProdutoDto> topage(Page<Produto> objetos) {

		return objetos.map(obj -> toDto(obj));
	}
}
