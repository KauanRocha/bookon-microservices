package br.com.bookon.loan.payloads.responses;

import br.com.bookon.server.models.mongo.BookMongo;

public class BookResponse {

	private BookMongo book;

	public Long getId() {
		return book.getId();
	}
	
	public String getTitle() {
		return book.getTitle();
	}

	public BookResponse(BookMongo book) {
		this.book = book;
	}
	
}
