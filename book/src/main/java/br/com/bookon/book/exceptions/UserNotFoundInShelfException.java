package br.com.bookon.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundInShelfException extends RuntimeException {

    public UserNotFoundInShelfException(Integer userId, String shelfId) {
    }

    public UserNotFoundInShelfException(String message, Throwable cause) {
        super(message, cause);
    }
}
