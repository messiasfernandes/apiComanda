package com.comanda.api;

import org.springframework.http.ResponseEntity;

import com.comanda.model.recorddto.Ean13Record;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name ="Gerador de Ean13" )
public interface GerarEan13OpenApi extends ControllerCroossOring {

	
	ResponseEntity<Ean13Record> gerarCodioEan13();

		
	
}
