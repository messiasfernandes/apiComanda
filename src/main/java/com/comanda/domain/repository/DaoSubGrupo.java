package com.comanda.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.SubGrupo;

@Repository
public interface DaoSubGrupo extends JpaRepository<SubGrupo, Long> {
	@EntityGraph(attributePaths = { "grupo" }, type = EntityGraphType.FETCH)
	@Query("SELECT DISTINCT s FROM SubGrupo s  " 
			+ " WHERE s.nomeSubgrupo LIKE %:parametro% " + " OR  s.grupo.nomeGrupo LIKE  %:parametro% ")

	Page<SubGrupo> search(@Param("parametro") String parametro, Pageable pageable);

	@Query("from SubGrupo s where s.nomeSubgrupo = :nome")
	SubGrupo buscar(String nome);

	@Query("SELECT DISTINCT s FROM SubGrupo s LEFT JOIN FETCH s.grupo WHERE s.id = :parametro ")
	Page<SubGrupo> pesquisarpoId(@Param("parametro") Long parametro, Pageable pageable);

     @EntityGraph(attributePaths = { "grupo" }, type = EntityGraphType.FETCH)
	  
    @Query("from SubGrupo s   join s.grupo where s.id = :id ")
	Optional<SubGrupo> buscarporId(Long id);

}
