package com.comanda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.entity.SubGrupo;
import com.comanda.domain.repository.DaoSubGrupo;
import com.comanda.domain.sservice.exeption.NegocioException;
import com.comanda.domain.sservice.exeption.RegistroNaoEncontrado;
import com.comanda.utils.ServiceFuncoes;
import com.comanda.utils.TolowerCase;

import jakarta.transaction.Transactional;

@Service
public class SubGrupoService  extends ServiceFuncoes implements ServiceModel<SubGrupo>{
	@Autowired
	private DaoSubGrupo daoSubGrupo;

	@Override
	public Page<SubGrupo> buscar(String nome, Pageable pageable) {
		Page<SubGrupo> page = null;
	if (!ehnumero(nome) && (qtdecaraceteres(nome) >= 0)) {
			nome = TolowerCase.normalizarString(nome);
     	page = daoSubGrupo.search(nome, pageable);
		}
		if ((ehnumero(nome)) && (qtdecaraceteres(nome) != 13)) {
			Long id = Sonumero(nome);
			System.out.println("pasoo"+ id);
			page = daoSubGrupo.pesquisarpoId(id, pageable);
		}
		
	
		return page;
		
	}


	
	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public SubGrupo buccarporid(Long id) {
		
		return daoSubGrupo.findById(id).orElseThrow(()-> new RegistroNaoEncontrado("Registro não entcontrado") );
	}

	@Transactional
	@Override
	public SubGrupo salvar(SubGrupo objeto) {
		var nome =  TolowerCase.normalizarString( objeto.getNomeSubgrupo());
		
		SubGrupo subGrupoExistente = daoSubGrupo.buscar(nome);
		if (subGrupoExistente != null && !subGrupoExistente.equals(objeto)) {
			throw new NegocioException("SubGrupo já cadastrada no banco de dados");
		}

		if (subGrupoExistente == null) {
			return daoSubGrupo.save(objeto);
		} else {
			return subGrupoExistente;
		}

	}

}
