package com.comanda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Comanda;
@Repository
public interface ComandasRepository extends JpaRepository<Comanda, Long>{

}
