package com.comanda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.entity.Estoque;
import com.comanda.domain.repository.EstoqueRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceEstoque implements ServiceModel<Estoque> {
	@Autowired
   private EstoqueRepository estoqueRepository;
	@Override
	public Page<Estoque> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Estoque buccarporid(Long id) {

		return estoqueRepository.findById(id).get();
	}

	@Transactional
	@Override
	public Estoque salvar(Estoque objeto) {

		return estoqueRepository.save(objeto);
	}

}
