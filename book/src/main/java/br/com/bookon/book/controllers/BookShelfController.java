package br.com.bookon.book.controllers;

import br.com.bookon.book.payloads.request.BookShelfRequest;
import br.com.bookon.book.payloads.response.BookShelfResponse;
import br.com.bookon.book.service.BookShelfService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/shelves")
public class BookShelfController {

    private  final BookShelfService bookShelfService;

    @Autowired
    BookShelfController(BookShelfService bookShelfService) {
        this.bookShelfService = bookShelfService;
    }

    @PostMapping
    public BookShelfResponse create(@RequestBody @Valid BookShelfRequest bookShelfRequest) {
        return bookShelfService.create(bookShelfRequest);
    }
}
