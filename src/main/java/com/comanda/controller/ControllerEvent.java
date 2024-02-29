package com.comanda.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RestController;

import com.comanda.controller.event.RecursoCriadoEvent;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class ControllerEvent {
	@Autowired
	private ApplicationEventPublisher publisher;
	public void criaevento(Long codigoEvento, HttpServletResponse response)  {

		publisher.publishEvent(new RecursoCriadoEvent(this, response, codigoEvento));
	}
   
}
