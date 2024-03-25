package com.comanda.domain.repository;

import com.comanda.domain.entity.Componente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComponentesRepository extends JpaRepository<Componente, Long> {
	@Query(value = "SELECT coalesce(max(id), 0) FROM Componente") 
    public Long obterMaxId();
	
	 default Long obterProximoId() {
	        Long maxId = obterMaxId();
	        return maxId + 1;
	    }
}


