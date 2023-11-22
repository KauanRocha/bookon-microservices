package br.com.bookon.server.payload.response.postgres;

import br.com.bookon.server.enumerations.BookCategoryEnum;
import br.com.bookon.server.models.postgres.Book;

public class BookResponse {
	  private Book book;

	    public BookResponse(Book book) {
	        this.book = book;
	    }

	    public String getBookTitle() {
	        return book.getTitle();
	    }
	    
	    public String getBookAUthor() {
	        return book.getAuthor();
	    }

	    public BookCategoryEnum getBookCathegory() {
	        return book.getCategory();
	    }

	    public Integer getUserId() {
	        return book.getUser().getId();
	    }
	    
}
