package com.comanda;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comanda.domain.entity.Componente;
import com.comanda.domain.entity.Marca;
import com.comanda.domain.repository.ComponentesRepository;
import com.comanda.domain.repository.ProdutosRepository;

@SpringBootTest
class ApiComandaApplicationTests {
	private Set<Marca> marcas = new HashSet<>();
   private Set<Componente> componentes = new HashSet<>();
   @Autowired
   private ComponentesRepository  componentesRepository ;
   @Autowired
	private ProdutosRepository produtosRepository;
	void contextLoads() {
		var marca = new Marca();
//		marca.setId(1l);
		marca.setNomeMarca("Adidas");
		var marca1 = new Marca();
	//	marca1.setId(2l);
		marca1.setNomeMarca("Nike");
		var marca2 = new Marca();
	//	marca2.setId(3l);
		marca2.setNomeMarca("Coca Cola");
		marcas.add(marca);
		System.out.println(marcas.size());
		marcas.add(marca1);
		marcas.add(marca);
		System.out.println(marcas.size());
		marcas.add(marca2);
		marcas.add(marca);
		System.out.println(marcas.size());
		for (Marca marca3 : marcas) {
			System.out.println(marca3.getId());
			System.out.println(marca3.getNomeMarca());

		}
	}
	@Test
	void TestarSet() {
	//	Long id= componentesRepository.getMaxId()+1l;
	//	System.out.println(id++);
		var produto = produtosRepository.findById(10l);
       	
		Componente c1 = new Componente();
		c1.setId(componentesRepository.obterProximoId());
		c1.setProduto(produto.get());
		var produto2 = produtosRepository.findProdutoWithDetalhesAndComponentes(33l).get();
		Componente c2 = new Componente();
		c2.setId(componentesRepository.obterProximoId());
		System.out.println(c2.getId());
		c2.setQtde(new BigDecimal(10));
		c2.setProduto(produto2);
		componentes.add(c1);
		System.out.println(componentes.size());
		componentes.add(c2);
		System.out.println(componentes.size());
		for (Componente componete: componentes) {
			System.out.println(componete.getId());
			System.out.println(componete.getProduto().getNome());
		}
		
	}

}
