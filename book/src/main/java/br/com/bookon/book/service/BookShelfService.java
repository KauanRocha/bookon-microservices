package br.com.bookon.book.service;

import br.com.bookon.book.models.mongo.Book;
import br.com.bookon.book.models.postgre.BookShelf;
import br.com.bookon.book.repositories.BookShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookShelfService {


    private final BookService bookService;

    private final BookShelfRepository bookShelfRepository;

    @Autowired
    public BookShelfService(BookService bookService, BookShelfRepository bookShelfRepository) {
        this.bookService = bookService;
        this.bookShelfRepository = bookShelfRepository;
    }


    public void create(BookShelf bookShelfRequest) {
        bookShelfRepository.save(bookShelfRequest);
    }
}
