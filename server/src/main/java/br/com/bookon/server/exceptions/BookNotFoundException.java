package br.com.bookon.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6227506126459313730L;
	
	@ExceptionHandler(value = BookNotFoundException.class)
	   public ResponseEntity<String> NotFoundException(BookNotFoundException exception) {
	      return new ResponseEntity<>("book-not-found", HttpStatus.NOT_FOUND);
	   }
}
