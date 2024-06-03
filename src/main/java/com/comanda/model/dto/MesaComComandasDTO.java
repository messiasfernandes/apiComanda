package com.comanda.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.comanda.domain.entity.Comanda;
import com.comanda.domain.entity.Mesa;

import lombok.Data;

@Data
public class MesaComComandasDTO {
	  private Mesa mesa;
	    private List<Comanda> comandas;
	    private BigDecimal total;

	    public MesaComComandasDTO(Mesa mesa) {
	        this.mesa = mesa;
	        this.comandas = new ArrayList<>();
	        this.total = BigDecimal.ZERO;
	    }

	    public void addComanda(Comanda comanda) {
	        this.comandas.add(comanda);
	        this.total = this.total.add(comanda.getTotal());
	    }
}
