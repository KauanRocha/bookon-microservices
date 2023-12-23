package br.com.bookon.book.repositories;

import br.com.bookon.book.models.postgre.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Integer> {
}
