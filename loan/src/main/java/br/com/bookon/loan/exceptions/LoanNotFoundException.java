package br.com.bookon.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException() {
        super("loan-not-found");
    }

    public LoanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
