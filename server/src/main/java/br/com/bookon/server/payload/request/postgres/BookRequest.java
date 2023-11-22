package br.com.bookon.server.payload.request.postgres;

import br.com.bookon.server.enumerations.BookCategoryEnum;
import br.com.bookon.server.models.postgres.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequest {

	@NotBlank(message="{fieldName}-is-mandatory")
    @NotNull
    private String title;

	@NotBlank(message="{fieldName}-is-mandatory")
    @NotNull
    private String author;

    @NotNull
    private BookCategoryEnum category;


    public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public BookCategoryEnum getCategory() {
		return category;
	}


	public void setCategory(BookCategoryEnum category) {
		this.category = category;
	}


	public Book build() {
    	Book book = new Book();
        book.setTitle(title);
    	book.setAuthor(author);
    	book.setCategory(category);
    	
    	return book;
    }
}
