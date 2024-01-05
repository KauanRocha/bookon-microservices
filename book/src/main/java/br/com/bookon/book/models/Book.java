package br.com.bookon.book.models;

import br.com.bookon.book.enums.BookCategoryEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document( collection = "books")
public class Book extends DateHelper {

    @Id
    private String id;

    private String title;
    private String author;
    private BookCategoryEnum category;
    @Field(name = "id_book_shelf")

    private Integer bookShelfId;

    @Field(name = "id_user")
    private Integer userId;


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

    public Integer getBookShelfId() {
        return bookShelfId;
    }

    public void setBookShelfId(Integer bookShelfId) {
        this.bookShelfId = bookShelfId;
    }
}