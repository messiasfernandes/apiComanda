package com.comanda.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.Marca;
import com.comanda.model.dto.MarcaDto;
import com.comanda.model.input.MarcaInput;



@Component
public class MarcaProdutoConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public MarcaDto toDto(Marca objeto) {

		return modelMapper.map(objeto, MarcaDto.class);
	}

	public Marca toEntity(MarcaInput objeto) {

		return modelMapper.map(objeto, Marca.class);
	}

	public List<MarcaDto> toCollectionDto(List<Marca> subgrupos) {
		return subgrupos.stream().map(this::toDto).collect(Collectors.toList());
	}

	public Page<MarcaDto> topage(Page<Marca> objetos) {

		return objetos.map(obj -> toDto(obj));
	}


}
