package com.comanda.api;

import org.springframework.http.ResponseEntity;

import com.comanda.model.recorddto.Ean13Record;

public interface GerarEan13OpenApi extends ControllerCroossOring {

	
	ResponseEntity<Ean13Record> gerarCodioEan13();

		
	
}