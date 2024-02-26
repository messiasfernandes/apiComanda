package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.comanda.domain.entity.Produto_CodigoBarras;
import com.comanda.domain.entity.Variacao;

import jakarta.persistence.Transient;
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

	private BigDecimal precovenda;
    private SubGrupoDTO subgrupo;
	private Set<Produto_CodigoBarras> produtos_codigo = new HashSet<>();
	private List<Variacao> variacoes =  new ArrayList<>();


	private BigDecimal preco;

	
}
