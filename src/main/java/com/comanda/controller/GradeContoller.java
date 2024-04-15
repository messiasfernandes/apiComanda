package com.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.domain.entity.Grade;
import com.comanda.domain.repository.GradesRepository;
@RestController
@RequestMapping("/grades")
public class GradeContoller {
	@Autowired
   private GradesRepository gradesRepository;
	@GetMapping
	public ResponseEntity<List<Grade>>lista(){
		return ResponseEntity.status(HttpStatus.OK).body(gradesRepository.buscartos());
		
	}
}
