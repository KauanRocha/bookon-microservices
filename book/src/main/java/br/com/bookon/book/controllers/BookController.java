package br.com.bookon.book.controllers;

import br.com.bookon.book.payloads.request.BookRequest;
import br.com.bookon.book.payloads.response.BookResponse;
import br.com.bookon.book.payloads.response.RegionWithBookRosponse;
import br.com.bookon.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestParam Long userId, @Valid @RequestBody BookRequest bookRequest){
        return new ResponseEntity<>(bookService.createBook(bookRequest, userId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getBookById(@RequestParam Long userId) {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String id, @RequestBody BookRequest updatedBook) {
        return new ResponseEntity<>(bookService.updateBook(id, updatedBook), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/regions/geolocation")
    public ResponseEntity<List<RegionWithBookRosponse>> getBookByGeolocation(@RequestParam Long userId){
        return new ResponseEntity<>(bookService.findRegionsWithNearbyBooksByUserGeolocation(userId), HttpStatus.OK);
    }

    @GetMapping("/regions/address")
    public ResponseEntity<List<RegionWithBookRosponse>> getBookByAddress(@RequestParam Integer userId, @RequestBody String address){
        return new ResponseEntity<>(bookService.findRegionsWithNearbyBooksByAddress(userId, address), HttpStatus.OK);
    }

}