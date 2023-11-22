package br.com.bookon.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoanNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6227506126459313730L;
	
	@ExceptionHandler(value = LoanNotFoundException.class)
	   public ResponseEntity<String> NotFoundException(LoanNotFoundException exception) {
	      return new ResponseEntity<>("loan-not-found", HttpStatus.NOT_FOUND);
	   }
}
