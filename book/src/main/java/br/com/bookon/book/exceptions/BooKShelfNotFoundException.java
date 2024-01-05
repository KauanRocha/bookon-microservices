package br.com.bookon.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BooKShelfNotFoundException extends RuntimeException {

    public BooKShelfNotFoundException(String shelfId) {
        super("shelfId-"+ shelfId + "-not-found");
    }

    public BooKShelfNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
