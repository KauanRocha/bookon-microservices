package br.com.bookon.book.models.mongo;

import br.com.bookon.book.enums.BookCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "books")
public class Book {

    @Id
    private String id;

    private String title;
    private String author;
    private BookCategoryEnum category;
    private Integer bookShelfId;

}