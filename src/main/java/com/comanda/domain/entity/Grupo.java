package com.comanda.domain.entity;



import com.comanda.utils.TolowerCase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_grupo")
public class Grupo extends GeradorId{
	
	private static final long serialVersionUID = 1L;
	@Setter(value = AccessLevel.NONE)
	@NotBlank
	@Column(length = 45 , nullable = false)
	private String nomeGrupo;
	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo =TolowerCase.normalizarString(nomeGrupo);
	}
	
  
}
