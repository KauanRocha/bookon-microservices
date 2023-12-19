package br.com.bookon.book.payloads.request;

import br.com.bookon.book.enums.BookCategoryEnum;

public class BookRequest {
    private String title;
    private String author;
    private BookCategoryEnum category;

    public BookRequest() {
    }

    public BookRequest(String title, String author, BookCategoryEnum category, Long userId) {
        this.title = title;
        this.author = author;
        this.category = category;
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
    }
