
package com.comanda.domain.entity;



import com.comanda.utils.TolowerCase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_subrupo")
public class SubGrupo  extends GeradorId{

	private static final long serialVersionUID = 1L;
	@Setter(value = AccessLevel.NONE)

	@Column(length = 45 )
	private String nomeSubgrupo;                                                                                                                                                                
	public void setNomeSubgrupo(String nomeSubgrupo) {
		this.nomeSubgrupo = TolowerCase.normalizarString(nomeSubgrupo);
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Grupo grupo;

}
