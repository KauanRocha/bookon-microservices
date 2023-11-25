package br.com.bookon.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6227506126459313730L;
	
	@ExceptionHandler(value = UserNotFoundException.class)
	   public ResponseEntity<String> NotFoundException(UserNotFoundException exception) {
	      return new ResponseEntity<>("user-not-found", HttpStatus.NOT_FOUND);
	   }
}
