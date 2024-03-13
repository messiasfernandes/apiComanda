package com.comanda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.entity.Marca;
import com.comanda.domain.repository.MarcasRepository;
import com.comanda.domain.service.exeption.EntidadeEmUsoExeption;
import com.comanda.domain.service.exeption.NegocioException;
import com.comanda.domain.service.exeption.RegistroNaoEncontrado;
import com.comanda.utils.TolowerCase;



@Service
public class MarcaService implements ServiceModel<Marca> {
	@Autowired
	private MarcasRepository marcasRepository;

	@Override
	public Page<Marca> buscar(String nome, Pageable pageable) {
		nome = TolowerCase.normalizarString(nome);
		return marcasRepository.search(nome, pageable);
	}

	@Override
	public void excluir(Long codigo) {
		buccarporid(codigo);
		try {
			marcasRepository.deleteById(codigo);
			marcasRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					"Operação não permitida!! Este registro pode estar asssociado a outra tabela");
		}

	}

	@Override
	public Marca buccarporid(Long id) {
		if (marcasRepository.findById(id).isEmpty()) {
			throw new RegistroNaoEncontrado("Subcategoria não encotrada");
		}
		return marcasRepository.findById(id).get();
	}

	@Override
	public Marca salvar(Marca objeto) {
		var nome = TolowerCase.normalizarString(objeto.getNomeMarca());

		Marca marcaExistente = marcasRepository.buscar(nome);
		if (marcaExistente != null && !marcaExistente.equals(objeto)) {
			throw new NegocioException("Marca já cadastrada no banco de dados");
		}

		if (marcaExistente == null) {
			return marcasRepository.save(objeto);
		} else {
			return marcaExistente;
		}

	}

}
