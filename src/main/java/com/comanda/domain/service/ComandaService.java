package com.comanda.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.entity.Comanda;
import com.comanda.domain.enumerado.StatusPagamentoComanda;
import com.comanda.domain.repository.ComandasRepository;
import com.comanda.model.dto.ComandaDTo;
import com.comanda.model.dto.MesaComComandasDTO;

@Service
public class ComandaService implements ServiceModel<Comanda> {
	@Autowired
    private ComandasRepository comandaRepository;

	 public List<Comanda> listarComandasAbertas() {
	        List<StatusPagamentoComanda> statusPagos = Arrays.asList(StatusPagamentoComanda.PAGO, StatusPagamentoComanda.CANCELADO);
	        return comandaRepository.findComandasAbertas(statusPagos);
	    }

	    public List<MesaComComandasDTO> calcularTotalComandasAbertasPorMesa() {
	        List<Comanda> comandas = listarComandasAbertas();
	        Map<Long, MesaComComandasDTO> mesaComandasMap = new HashMap<>();

	        for (Comanda comanda : comandas) {
	            Long mesaId = comanda.getMesa().getId();
	            MesaComComandasDTO mesaComandasDTO = mesaComandasMap.getOrDefault(mesaId, new MesaComComandasDTO(comanda.getMesa()));

	            mesaComandasDTO.addComanda(comanda);
	            mesaComandasMap.put(mesaId, mesaComandasDTO);
	        }

	        return new ArrayList<>(mesaComandasMap.values());
	    }


	    public List<ComandaDTo> calcularTotal() {
	    return comandaRepository.buscarComandasTotal();
	    }
    
	@Override
	public Page<Comanda> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comanda buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comanda salvar(Comanda objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
