package com.comanda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Comanda;
import com.comanda.domain.enumerado.StatusPagamentoComanda;
import com.comanda.model.dto.ComandaDTo;
@Repository
public interface ComandasRepository extends JpaRepository<Comanda, Long>{
	  @Query("SELECT c FROM Comanda c JOIN FETCH c.mesa m WHERE c.statusPagamentoComanda NOT IN (:statusPagos) AND m.statusMesa = 'Ocupada'")
	    List<Comanda> findComandasAbertas(@Param("statusPagos") List<StatusPagamentoComanda> statusPagos);
	  
	  @Query("SELECT new com.comanda.model.dto.ComandaDTo(c.id, c.data_abertura, c.statusPagamentoComanda, " +
		       "new com.comanda.model.dto.MesaDto(m.id, m.statusMesa, m.numerodaMesa, m.capacidade), SUM(ic.subtotal) as total) " +
		       "FROM Comanda c JOIN c.itemsdaComanda ic JOIN c.mesa m " +
		       "WHERE c.statusPagamentoComanda NOT IN (com.comanda.domain.enumerado.StatusPagamentoComanda.PAGO, "
		       + "com.comanda.domain.enumerado.StatusPagamentoComanda.CANCELADO) " +
		       "GROUP BY c.id, c.data_abertura, c.statusPagamentoComanda, m.id,  m.statusMesa, m.numerodaMesa, m.capacidade")
		List<ComandaDTo> buscarComandasTotal();
	  
	  @Query("SELECT c FROM Comanda c JOIN FETCH c.mesa m  JOIN FETCH c.itemsdaComanda ic  JOIN FETCH ic.produtoDetalhe  "
	  		+ "WHERE     m.id = :numeroMesa")
	  List<Comanda> detalharComanda (Integer numeroMesa);
	  
	  
	  @Query("SELECT new com.comanda.model.dto.ComandaDTo(c.id, c.data_abertura, c.statusPagamentoComanda, " +
		       "new com.comanda.model.dto.MesaDto(m.id, m.statusMesa, m.numerodaMesa , m.capacidade), SUM(ic.subtotal) as total) " +
		       "FROM Comanda c JOIN c.itemsdaComanda ic JOIN c.mesa m " +
		       "WHERE   m.id = :numeroMesa  " +
		       "GROUP BY c.id, c.data_abertura, c.statusPagamentoComanda, m.id,  m.statusMesa, m.numerodaMesa, m.capacidade")
	  List<ComandaDTo> buscarComandasPorNumeroMesa(@Param("numeroMesa") Integer numeroMesa);
}
