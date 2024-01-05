package br.com.bookon.book.service;

import br.com.bookon.book.exceptions.UserNotFoundInShelfException;
import br.com.bookon.book.models.Book;
import br.com.bookon.book.payloads.request.BookRequest;
import br.com.bookon.book.payloads.response.BookResponse;
import br.com.bookon.book.repositories.BookRepository;
import br.com.bookon.book.repositories.BookShelfRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final WebClient.Builder webClient;

    private final BookShelfRepository bookShelfRepository;


    @Autowired
    public BookService(BookRepository bookRepository, WebClient.Builder webClient, BookShelfRepository  bookShelfRepository, BookShelfRepository bookShelfRepository1) {
        this.bookRepository = bookRepository;
        this.webClient = webClient;
        this.bookShelfRepository = bookShelfRepository1;
    }

    @Transactional
    public BookResponse createBook(BookRequest bookRequest, Integer userId){
        isUserInShelf(userId, bookRequest.getBookShelfId());
        return convertToBookResponse(bookRepository.save(convertToBook(bookRequest)));
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToBookResponse)
                .toList();
    }

    public List<BookResponse> getBookByUserId(Integer userId) {
        List<Book> books = bookRepository.findBookByUserId(userId);
        return books.stream()
                .map(this::convertToBookResponse)
                .toList();
    }

    @Transactional
    public BookResponse updateBook(String id, BookRequest bookRequest) {
        Book existingBook = bookRepository.findBookById(id);
        BeanUtils.copyProperties(bookRequest, existingBook);
        return convertToBookResponse(bookRepository.save(existingBook));
    }

    @Transactional
    public String deleteBook(String id) {
        Book existingBook = bookRepository.findBookById(id);
        return delete(existingBook);
    }

//    public List<RegionWithBookRosponse> findRegionsWithNearbyBooksByUserGeolocation(Long userFinderId) {
//
//        List<RegionWithUsersResponse> regionsWithUsers = webClient.get().uri("http://localhost:/8081/api/users/geolocation/"+userFinderId)
//                .retrieve()
//                .bodyToFlux(RegionWithUsersResponse.class)
//                .collectList()
//                .block();
//
//        return regionsWithUsers.stream()
//                .map(regionMapper::convertToRegionWithBook)
//                .collect(Collectors.toList());
//    }
//
//    public List<RegionWithBookRosponse> findRegionsWithNearbyBooksByAddress(Integer userFinderId, String address) {
//
//        List<RegionWithUsersResponse> regionsWithUsers = webClient.get().uri("http://localhost:/8081/api/users/address/"+address)
//                .retrieve()
//                .bodyToFlux(RegionWithUsersResponse.class)
//                .collectList()
//                .block();
//
//        return regionsWithUsers.stream()
//                .map(regionMapper::convertToRegionWithBook)
//                .collect(Collectors.toList());
//    }
    private @NotNull BookResponse convertToBookResponse(Book book) {
        var bookResponse = new BookResponse();
        BeanUtils.copyProperties(book, bookResponse);

        return bookResponse;
    }

    private @NotNull Book convertToBook(BookRequest bookRequest) {
        var book = new Book();
        BeanUtils.copyProperties(bookRequest, book);

        return book;
    }

    private Boolean isUserInShelf(Integer userId, String shelfId) {
        Boolean isUserInShelf = bookShelfRepository.existsBookShelfByUserIdAndId(userId, shelfId);
        if(isUserInShelf.equals(Boolean.FALSE)){
            throw new UserNotFoundInShelfException(userId, shelfId);
        }

        return Boolean.TRUE;
    }

    private String delete(@NotNull Book book) {
        book.setDeleted(true);
        bookRepository.save(book);
        return book.getId();
    }
}