package com.comanda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Grade;
@Repository
public interface GradesRepository extends JpaRepository<Grade, Long> {
	@EntityGraph(attributePaths = {"caracteristicas"} )
	@Query("select g from Grade g")
     List<Grade> buscartos();
}
