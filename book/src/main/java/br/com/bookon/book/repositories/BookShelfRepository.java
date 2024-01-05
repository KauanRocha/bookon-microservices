package br.com.bookon.book.repositories;

import br.com.bookon.book.models.BookShelf;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookShelfRepository extends MongoRepository<BookShelf, String> {

    Optional<BookShelf> findByUserId(Integer integer);

    boolean existsBookShelfByUserIdAndId(Integer id, String userId);

    List<BookShelf> findAllByIdIs(List<String> ids);


}
