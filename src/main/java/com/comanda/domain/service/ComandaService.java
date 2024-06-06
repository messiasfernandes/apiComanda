package com.comanda.domain.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.entity.Comanda;
import com.comanda.domain.enumerado.StatusPagamentoComanda;
import com.comanda.domain.repository.ComandasRepository;
import com.comanda.domain.repository.ItemdaComandaRepository;
import com.comanda.model.dto.ComandaDTo;
import com.comanda.model.dto.ItemdaComandaDTo;
import com.comanda.model.dto.MesaDto;

@Service
public class ComandaService implements ServiceModel<Comanda> {
	@Autowired
	private ComandasRepository comandaRepository;
	@Autowired
	private ItemdaComandaRepository itemdaComandaRepository;

	public List<Comanda> listarComandasAbertas() {
		List<StatusPagamentoComanda> statusPagos = Arrays.asList(StatusPagamentoComanda.PAGO,
				StatusPagamentoComanda.CANCELADO);
		return comandaRepository.findComandasAbertas(statusPagos);
	}

	public List<ComandaDTo> buscarComandasPorNumeroMesa(Integer numeroMesa) {
		List<ComandaDTo> comandas = comandaRepository.buscarComandasPorNumeroMesa(numeroMesa);

		for (ComandaDTo comanda : comandas) {
			List<ItemdaComandaDTo> items = itemdaComandaRepository.buscarItensDaComanda(comanda.getId());

			BigDecimal total = items.stream()
					.map(item -> item.getSubtotal()
							.subtract(item.getProdutoDetalhe().getDesconto().multiply((item.getQuantidade()))))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			comanda.setItemsdaComanda(items);
			comanda.setTotal(total);
		}

		return comandas;
	}

	public List<ComandaDTo> calcularTotal() {
		return comandaRepository.buscarComandasTotal();
	}

	@Override
	public Page<Comanda> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Comanda> detalhar(Integer id) {

		return comandaRepository.detalharComanda(id);
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
