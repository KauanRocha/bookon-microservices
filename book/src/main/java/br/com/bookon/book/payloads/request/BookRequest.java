package br.com.bookon.book.payloads.request;

import br.com.bookon.book.enums.BookCategoryEnum;
import br.com.bookon.book.models.BookShelf;
import jakarta.validation.constraints.NotNull;

public class BookRequest {

    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private BookCategoryEnum category;
    @NotNull
    private String bookShelfId;

    public BookRequest() {
    }

    public BookRequest(String title, String author, BookCategoryEnum category, String bookShelfId) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.bookShelfId = bookShelfId;
    }

    // Getters e Setters
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

    public String getBookShelfId() {
        return bookShelfId;
    }

    public void setBookShelfId(String bookShelfId) {
        this.bookShelfId = bookShelfId;
    }
}
