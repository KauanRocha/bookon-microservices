package br.com.bookon.book.models.postgre;

import br.com.bookon.book.models.mongo.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document( collection = "book_shelf")
public class BookShelf {

    @Id
    private String id;
    private String name;

    private Double latitude;

    private Double longitude;

    private Integer userId;

    private final Set<Book> books = new HashSet<>();

}
