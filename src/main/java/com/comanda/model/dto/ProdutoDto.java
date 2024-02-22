package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.comanda.domain.entity.Produto_CodigoBarras;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
@Data
public class ProdutoDto {
	private Long id;
	private String nome;
	
	private String descricao;

	private EstoqueDto estoque;

	private MarcaDto marca;
	@Getter(value = AccessLevel.NONE)
	private BigDecimal precovenda;

	private PrecoDto preco;
//	public BigDecimal getPrecovenda() {
//		if(preco==null) {
//			return precovenda;
//		}else {
//			return preco.getPrecovenda();	
//		}
//		
//	}
	private Set<Produto_CodigoBarras> produtos_codigo = new HashSet<>();
}
