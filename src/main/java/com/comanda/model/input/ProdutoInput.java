package com.comanda.model.input;

import java.util.HashSet;
import java.util.Set;

import com.comanda.domain.entity.Componente;
import com.comanda.domain.enumerado.TipoProduto;
import com.comanda.utils.TolowerCase;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class ProdutoInput {

	
	

	private Long id;
	@Setter(value = AccessLevel.NONE)
	@NotNull
	@NotBlank(message = "Campo  obrigatório")
	private String nome;
	@Setter(value = AccessLevel.NONE)
	private String descricao;
	private String imagem;
	private EstoqueInput estoque;
//	@JsonIgnoreProperties(value = { "nomeMarca" }, allowGetters = true)
	private MarcaInput marca ;
	private PrecoInput preco;
	private SubGrupoInput subgrupo;
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;
	private String codigoFabricante;

	public void setNome(String nome) {
		this.nome = TolowerCase.normalizarString(nome);
	}

	public void setDescricao(String descricao) {
		this.descricao = TolowerCase.normalizarString(descricao);
	}

	@Valid
	private Set<produtoDetalheInput> produtoDetalhe = new HashSet<>();
	
	
	private Set<Componente> componentes = new HashSet<>();

	@Override
	public String toString() {
		return "ProdutoInput [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", imagem=" + imagem
				+ ", estoque=" + estoque + ", marca=" + marca + ", preco=" + preco + ", subgrupo=" + subgrupo
				+ ", tipoProduto=" + tipoProduto + ", codigoFabricante=" + codigoFabricante + ", produtoDetalhe="
				+ produtoDetalhe + ", componentes=" + componentes + "]";
	}

//	public void definirIdsAutomaticamente() {
//		
////	//	Long proximoId = componentesRepository.obterProximoId();
////		for (ComponenteInput componente : comoponentista) {
////			if(componente.getId()==null) {
////				componente.setId(proximoId);	
////			}
////			
////			proximoId++;
////		}
//	}

//	private Long obterProximoIdComponente() {
//		if (componentes.isEmpty()) {
//			return 1L; // Se a lista estiver vazia, retorna 1 como próximo ID
//		} else {
//			return componentes.stream().mapToLong(ComponenteInput::getId).max().orElse(0L) + 1;
//		}
//	}
//	
	
}