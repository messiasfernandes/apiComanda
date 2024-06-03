package com.comanda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Comanda;
import com.comanda.domain.enumerado.StatusPagamentoComanda;
@Repository
public interface ComandasRepository extends JpaRepository<Comanda, Long>{
	  @Query("SELECT c FROM Comanda c JOIN FETCH c.mesa m WHERE c.statusPagamentoComanda NOT IN (:statusPagos) AND m.statusMesa = 'Ocupada'")
	    List<Comanda> findComandasAbertas(@Param("statusPagos") List<StatusPagamentoComanda> statusPagos);
	}

