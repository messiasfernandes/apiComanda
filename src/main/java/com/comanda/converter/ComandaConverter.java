package com.comanda.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.Comanda;
import com.comanda.model.dto.ComandaDTo;
@Component
public class ComandaConverter {
	@Autowired
	private ModelMapper modelMapper;

	public ComandaDTo toDto(Comanda objeto) {

		return modelMapper.map(objeto, ComandaDTo.class);
	}

//	public Marca toEntity(MarcaInput objeto) {
//
//		return modelMapper.map(objeto, Marca.class);
//	}

	public List<ComandaDTo> toCollectionDto(List<Comanda> comandas) {
		return comandas.stream().map(this::toDto).collect(Collectors.toList());
	}
}
