package br.com.bookon.book.models;

import br.com.bookon.book.enums.BookCategoryEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document( collection = "books")
public class Book {

    @Id
    private String id;

    private String title;
    private String author;
    private BookCategoryEnum category;
    private Long userId;

    public Book() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Book(String title, String author, BookCategoryEnum category, Long userId) {
        super();
        this.title = title;
        this.author = author;
        this.category = category;
        this.userId = userId;
    }
}