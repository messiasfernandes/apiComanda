package com.comanda.model.recorddto;

import com.comanda.domain.entity.Grupo;

public record DetalharGrupoR( Long id , String nomeGrupo) {
       public DetalharGrupoR(Grupo grupo) {
    	  this(grupo.getId(), grupo.getNomeGrupo());
	}
}
