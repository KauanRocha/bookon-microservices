package br.com.bookon.server.repositories.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.bookon.server.models.postgres.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor<Book>, ListCrudRepository<Book, Long> {

    Book findBookById(Long id);
    
    List<Book> findBookByUserId(Integer userId);
    
}
