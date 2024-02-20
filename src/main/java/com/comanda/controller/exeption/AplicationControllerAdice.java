package com.comanda.controller.exeption;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.comanda.domain.sservice.exeption.RegistroNaoEncontrado;

@RestControllerAdvice
public class AplicationControllerAdice   {
	@Autowired
	private MessageSource messageSource;
	@ExceptionHandler(RegistroNaoEncontrado.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String HandleNotFoudExeption (RegistroNaoEncontrado ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<Problema.Campo> campos = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Problema.Campo(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        Problema problema = Problema.builder()
                .titulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
                .status(HttpStatus.BAD_REQUEST.value())
                .dataHora(OffsetDateTime.now())
                .campos(campos)
                .build();

        return ResponseEntity.badRequest().body(problema);
    }
}
