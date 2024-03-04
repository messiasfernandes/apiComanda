package com.comanda.model.recorddto;

import com.comanda.domain.entity.SubGrupo;

public record DetalharSubrgupoR(Long id, String nomeSugrupo, DetalharGrupoR grupo) {
	public DetalharSubrgupoR(SubGrupo subGrupo) {

		this(subGrupo.getId(), subGrupo.getNomeSubgrupo(),
				subGrupo.getGrupo() != null
						? new DetalharGrupoR(subGrupo.getGrupo().getId(), 
			   subGrupo.getGrupo().getNomeGrupo()) : null);
	}
}
