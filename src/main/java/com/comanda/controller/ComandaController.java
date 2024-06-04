package com.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.converter.ComandaConverter;
import com.comanda.domain.entity.Comanda;
import com.comanda.domain.service.ComandaService;
import com.comanda.model.dto.ComandaDTo;
import com.comanda.model.dto.MesaComComandasDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/comandas")
public class ComandaController {
	@Autowired
	private ComandaService comandaService;
	@Autowired
	private ComandaConverter comandaConverter;
    @GetMapping
	public ResponseEntity<List<ComandaDTo>> buscar(){
		return ResponseEntity.status(HttpStatus.OK).body( comandaService.calcularTotal());
	}
    
    @PostMapping
	public ResponseEntity<ComandaDTo> adicionar( @Valid @RequestBody Comanda comanda){
		return ResponseEntity.status(HttpStatus.OK).body(comandaConverter.toDto(comandaService.salvar(comanda)));
	}
    
  //  @GetMapping("/abertas/total-por-mesa")
    public ResponseEntity<List<MesaComComandasDTO>> calcularTotalComandasAbertasPorMesa() {
        List<MesaComComandasDTO> mesasComandas = comandaService.calcularTotalComandasAbertasPorMesa();
        return ResponseEntity.ok(mesasComandas);
    }
}

