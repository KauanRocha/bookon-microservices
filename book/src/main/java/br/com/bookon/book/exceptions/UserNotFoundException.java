package br.com.bookon.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer userId) {
        super("userId-"+ userId + "-not-found");
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
