package com.comanda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.comanda.domain.entity.Produto;

@SpringBootTest
class ApiComandaApplicationTests {

	@Test
	void contextLoads() {
		var p1= new  Produto();
		p1.setDescricao(null);
	}

}
