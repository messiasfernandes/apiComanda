package com.comanda.domain.entity;

import com.comanda.utils.TolowerCase;

//mport br.com.omnisoftapi.utils.TolowerCase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tab_marca")

public class Marca extends GeradorId {

	
	private static final long serialVersionUID = 1L;

	@Setter(value = AccessLevel.NONE)
	@NotNull
	@Column(length = 60, nullable = false)
	private String nomeMarca;

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = TolowerCase.normalizarString(nomeMarca);
	}
}
