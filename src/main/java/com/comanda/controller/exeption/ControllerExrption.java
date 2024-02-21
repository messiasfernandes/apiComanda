package com.comanda.controller.exeption;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.comanda.domain.sservice.exeption.RegistroNaoEncontrado;

import jakarta.validation.ConstraintViolationException;




@RestControllerAdvice
public class ControllerExrption extends ResponseEntityExceptionHandler   {
	@Autowired
	private MessageSource messageSource;
   
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		var campos = new ArrayList<Problema.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new Problema.Campo(nome, mensagem));
		}

		var problema = Problema.builder().status(status.value())
				.titulo("Um ou mais campos estão inválidos. " + "Faça o preenchimento correto e tente novamente")
				.status(status.value()).dataHora(OffsetDateTime.now()).campos(campos).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
//	@ExceptionHandler({ ConstraintViolationException.class})
//	public ResponseEntity<Object> cpfoCnpjviolation (ConstraintViolationException ex,
//			WebRequest request) {
//		var status = HttpStatus.BAD_REQUEST;
//		var problema = Problema.builder().
//				status(status.value()).
//				titulo(ex.getMessage())
//				.dataHora(OffsetDateTime.now()).build();;
//
//		return new ResponseEntity<>( problema, status);
//	}

	@ExceptionHandler(RegistroNaoEncontrado.class)
	public ResponseEntity<Object> EntidadeNaoEncontrada(RegistroNaoEncontrado ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;

		var problema = Problema.builder().
				status(status.value()).
				titulo(ex.getMessage())
				.dataHora(OffsetDateTime.now()).build();

		return new ResponseEntity<>( problema, status);
	}
}

