package com.comanda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.ItemComanda;
import com.comanda.model.dto.ItemdaComandaDTo;

@Repository
public interface ItemdaComandaRepository  extends JpaRepository<ItemComanda, Long>{
	
    
	
	@Query("SELECT new com.comanda.model.dto.ItemdaComandaDTo( ic.quantidade, ic.subtotal, " +
		       "new com.comanda.model.dto.ProdutoDetalheDtoLista(pd.id, pd.codigobarras, pd.desconto , "
		       + "new com.comanda.model.dto.ProdutoDetalhado( p.id, p.nome, "
		       + " new com.comanda.model.dto.PrecoDto(pr.precovenda)    )    )) " +
		       "FROM ItemComanda ic  JOIN  ic.produtoDetalhe pd JOIN   pd.produto p  JOIN p.preco pr WHERE ic.comanda.id = :comandaId")
		List<ItemdaComandaDTo> buscarItensDaComanda(@Param("comandaId") Long comandaId);
}
