package br.com.bookon.book.service;

import br.com.bookon.book.models.Book;
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

import static br.com.bookon.book.enums.BookCategoryEnum.ROMANCE;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RegionMapper regionMapper;

    public BookResponse createBook(BookRequest bookRequest, Long userId){
        SimpleUserResponse userExists = webClientBuilder.build().get().uri("http://user/api/users/" + userId).retrieve().bodyToMono(SimpleUserResponse.class).block();
        Book book = bookMapper.convertToBook(bookRequest);
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

        List<RegionWithUsersResponse> regionsWithUsers = webClientBuilder.build().get().uri("http://localhost:/8081/api/users/geolocation/"+userFinderId)
                .retrieve()
                .bodyToFlux(RegionWithUsersResponse.class)
                .collectList()
                .block();

        return regionsWithUsers.stream()
                .map(regionMapper::convertToRegionWithBook)
                .collect(Collectors.toList());
    }

    public List<RegionWithBookRosponse> findRegionsWithNearbyBooksByAddress(Long userFinderId, String address) {

        List<RegionWithUsersResponse> regionsWithUsers = webClientBuilder.build().get().uri("http://localhost:/8081/api/users/address/"+address)
                .retrieve()
                .bodyToFlux(RegionWithUsersResponse.class)
                .collectList()
                .block();

        return regionsWithUsers.stream()
                .map(regionMapper::convertToRegionWithBook)
                .collect(Collectors.toList());
    }

}