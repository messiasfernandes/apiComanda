package com.comanda.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.model.recorddto.EstoqueMoventoListaDtoR;



@Component
public class EstoqueMovimemtoConvereter {



	public EstoqueMoventoListaDtoR toDto(EstoqueMovimento objeto) {

		return new EstoqueMoventoListaDtoR(objeto);
	}
//	public EstoqueMovimento toEntity(EstoqueMovimentoInput objeto) {
//
//		return modelMapper.map(objeto, EstoqueMovimento.class);
//	}
	public Page<EstoqueMoventoListaDtoR> topage(Page<EstoqueMovimento> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

	public List<EstoqueMoventoListaDtoR> toCollectionDto(List<EstoqueMovimento> movimentos) {
		return movimentos.stream().map(this::toDto).collect(Collectors.toList());
	}

}
