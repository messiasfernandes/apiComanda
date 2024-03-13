package com.comanda.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comanda.domain.entity.Marca;

public interface MarcasRepository extends JpaRepository<Marca, Long>{
	@Query("SELECT DISTINCT m FROM Marca m  "

			+ " WHERE m.nomeMarca LIKE %:parametro% ORDER BY m.nomeMarca")

	Page<Marca> search(@Param("parametro") String parametro, Pageable pageable);
	
	@Query("from Marca m where m.nomeMarca = :nome")
	Marca buscar(String nome);


}
