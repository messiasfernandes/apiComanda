package com.comanda.model.recorddto;

import com.comanda.domain.entity.Marca;

public record DetalharMarcaR ( Long id, String nomeMarca) {
   public DetalharMarcaR(Marca marca) {
	this(marca.getId(), marca.getNomeMarca());
}
}
