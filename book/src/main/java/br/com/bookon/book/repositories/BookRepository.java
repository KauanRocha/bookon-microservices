package br.com.bookon.book.repositories;


import br.com.bookon.book.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Book findBookById(String id);

    List<Book> findBookByUserId(Integer userId);

    List<Book> findBooksByUserIdIn(List<Integer> userIds);
}