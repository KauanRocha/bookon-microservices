package br.com.bookon.server.controllers;

import br.com.bookon.server.annotations.UserId;
import br.com.bookon.server.models.postgres.Book;
import br.com.bookon.server.payload.request.postgres.BookRequest;
import br.com.bookon.server.payload.response.postgres.BookResponse;
import br.com.bookon.server.payload.response.postgres.RegionWithBookRosponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.bookon.server.services.BookService;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@UserId Integer userId, @Valid @RequestBody BookRequest bookRequest){
    	return new ResponseEntity<>(bookService.createBook(bookRequest, userId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookResponse>> getBookById(@UserId Integer userId) {
    	return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return new ResponseEntity<>(bookService.updateBook(id, updatedBook), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/regions/geolocation")
    public ResponseEntity<List<RegionWithBookRosponse>> getBookByGeolocation(@UserId Integer userId){
    	return new ResponseEntity<>(bookService.findRegionsWithNearbyBooksByUserGeolocation(userId), HttpStatus.OK);
    }
    
    @GetMapping("/regions/address")
    public ResponseEntity<List<RegionWithBookRosponse>> getBookByAddress(@UserId Integer userId, @RequestBody String address){
    	return new ResponseEntity<>(bookService.findRegionsWithNearbyBooksByAddress(userId, address), HttpStatus.OK);
    }
    
}
