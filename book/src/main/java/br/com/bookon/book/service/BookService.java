package br.com.bookon.book.service;

import br.com.bookon.book.models.mongo.Book;
import br.com.bookon.book.payloads.mappers.BookMapper;
import br.com.bookon.book.payloads.mappers.RegionMapper;
import br.com.bookon.book.payloads.request.BookRequest;
import br.com.bookon.book.payloads.response.BookResponse;
import br.com.bookon.book.payloads.response.RegionWithBookRosponse;
import br.com.bookon.book.payloads.response.RegionWithUsersResponse;
import br.com.bookon.book.payloads.response.SimpleUserResponse;
import br.com.bookon.book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final WebClient webClient;

    private final RegionMapper regionMapper;



    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, WebClient webClient, RegionMapper regionMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.webClient = webClient;
        this.regionMapper = regionMapper;
    }

    public BookResponse createBook(BookRequest bookRequest, Long userId){

        Book book = bookMapper.convertToBook(bookRequest);
        SimpleUserResponse userExists = webClient.get().uri("http://localhost:8083/api/users/" + userId).retrieve().bodyToMono(SimpleUserResponse.class).block();

        book.setUserId(userId);
        return bookMapper.convertToBookResponse(bookRepository.save(book));
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(bookMapper::convertToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getBookByUserId(Long userId) {
        List<Book> books = bookRepository.findBookByUserId(userId);

        return books.stream()
                .map(bookMapper::convertToBookResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookResponse updateBook(String id, BookRequest updatedBook) {
        Book existingBook = bookRepository.findBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());

        return bookMapper.convertToBookResponse(bookRepository.save(existingBook));
    }

    @Transactional
    public void deleteBook(String id) {
        Book existingBook = bookRepository.findBookById(id);
        bookRepository.delete(existingBook);
    }

    public List<RegionWithBookRosponse> findRegionsWithNearbyBooksByUserGeolocation(Long userFinderId) {

        List<RegionWithUsersResponse> regionsWithUsers = webClient.get().uri("http://localhost:/8081/api/users/geolocation/"+userFinderId)
                .retrieve()
                .bodyToFlux(RegionWithUsersResponse.class)
                .collectList()
                .block();

        return regionsWithUsers.stream()
                .map(regionMapper::convertToRegionWithBook)
                .collect(Collectors.toList());
    }

    public List<RegionWithBookRosponse> findRegionsWithNearbyBooksByAddress(Integer userFinderId, String address) {

        List<RegionWithUsersResponse> regionsWithUsers = webClient.get().uri("http://localhost:/8081/api/users/address/"+address)
                .retrieve()
                .bodyToFlux(RegionWithUsersResponse.class)
                .collectList()
                .block();

        return regionsWithUsers.stream()
                .map(regionMapper::convertToRegionWithBook)
                .collect(Collectors.toList());
    }

}