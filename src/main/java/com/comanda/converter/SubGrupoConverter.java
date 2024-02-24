package com.comanda.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.SubGrupo;
import com.comanda.model.dto.SubGrupoDTO;
import com.comanda.model.input.SubGrupoInput;
@Component
public class SubGrupoConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public SubGrupoDTO toDto(SubGrupo objeto) {

		return modelMapper.map(objeto, SubGrupoDTO.class);
	}

	public SubGrupo toEntity(SubGrupoInput objeto) {

		return modelMapper.map(objeto, SubGrupo.class);
	}

	public List<SubGrupoDTO> toCollectionDto(List<SubGrupo> subgrupos) {
		return subgrupos.stream().map(this::toDto).collect(Collectors.toList());
	}

	public Page<SubGrupoDTO> topage(Page<SubGrupo> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

}
