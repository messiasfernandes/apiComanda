package com.comanda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.TipoMovimentacaoEstoque;
@Repository
public interface TiposdeMovimentaceosRepository extends JpaRepository<TipoMovimentacaoEstoque, Long> {

}
