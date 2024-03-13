package com.comanda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.api.GerarEan13OpenApi;
import com.comanda.model.recorddto.Ean13Record;
import com.comanda.utils.ServiceFuncoes;

@RestController
@RequestMapping("/gerarean13")
public class GerarEan13Controler implements GerarEan13OpenApi{
   @PostMapping
	@Override
	public ResponseEntity<Ean13Record> gerarCodioEan13() {
		ServiceFuncoes service = new ServiceFuncoes();
		var ean13Record = new Ean13Record(service.geraCodigoEan());
		return ResponseEntity.status(HttpStatus.CREATED).body(ean13Record);
	}

}
