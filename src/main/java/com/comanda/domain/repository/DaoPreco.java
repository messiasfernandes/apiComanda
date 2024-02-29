package com.comanda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Preco;
@Repository
public interface DaoPreco extends JpaRepository<Preco, Long> {

}
