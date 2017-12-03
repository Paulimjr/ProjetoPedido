package br.com.projeto.pedido.service.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {
	
	/**
	 * Monta response para exception da API 
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(
			ObjectNotFoundException e, HttpServletRequest request) {
		LocalDate date = LocalDate.now();
		String dataAtual = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dataAtual);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	/**
	 * Monta response para exception da API 
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, 
			HttpServletRequest request) {
		
		LocalDate date = LocalDate.now();
		String dataAtual = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), dataAtual);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
