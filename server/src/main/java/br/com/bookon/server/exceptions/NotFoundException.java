package br.com.bookon.server.exceptions;


public class NotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6227506126459313730L;

	public NotFoundException(String message) {
        super(message);
    }
}
