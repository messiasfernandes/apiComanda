
package com.comanda.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.model.dto.EstoqueMovimentoDTo;
import com.comanda.model.form.EstoqueMovimentoFormR;
import com.comanda.model.input.EstoqueMoviemtoInput;
import com.comanda.model.recorddto.EstoqueMoventoListaDtoR;



@Component
public class EstoqueMovimemtoConvereter {

	@Autowired
	private ModelMapper modelMapper;

	public EstoqueMovimentoDTo toDto(EstoqueMovimento objeto) {

		return modelMapper.map(objeto, EstoqueMovimentoDTo.class);
	}
//	public EstoqueMovimento toEntity(EstoqueMovimentoFormR objeto) {
//
//		return new EstoqueMovimento(objeto);
//	}
	public Page<EstoqueMovimentoDTo> topage(Page<EstoqueMovimento> objetos) {

		return objetos.map(obj -> toDto(obj));
	}
//
//	public List<EstoqueMoventoListaDtoR> toCollectionDto(List<EstoqueMovimento> movimentos) {
//		return movimentos.stream().map(this::toDto).collect(Collectors.toList());
//	}
//	
	public EstoqueMovimento paraEntidy(EstoqueMoviemtoInput objeto) {

		return modelMapper.map(objeto, EstoqueMovimento.class);
	}
	public List<EstoqueMovimento> toCollectionInput(List<EstoqueMoviemtoInput> movimentos) {
	List<EstoqueMovimento> movimentacoes = new ArrayList<>();
	    for (EstoqueMoviemtoInput movimentacao : movimentos) {
	    	movimentacoes.add(paraEntidy(movimentacao));
	    }
		return movimentacoes;
	}
	
	
}
