package com.comanda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Mesa;
@Repository
public interface MesasRepository  extends JpaRepository<Mesa, Long>{
	 @Query("select m from Mesa m join Comanda c on c.mesa.id = m.id")
	List<Mesa>buscarMesas();

}
