package br.com.bookon.book.service;

import br.com.bookon.book.exceptions.BooKShelfNotFoundException;
import br.com.bookon.book.exceptions.UserNotFoundException;
import br.com.bookon.book.models.BookShelf;
import br.com.bookon.book.payloads.request.BookShelfRequest;
import br.com.bookon.book.payloads.response.BookShelfResponse;
import br.com.bookon.book.repositories.BookShelfRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class BookShelfService {
    private final BookShelfRepository bookShelfRepository;

    private final WebClient webClient;

    @Autowired
    public BookShelfService(BookShelfRepository bookShelfRepository, WebClient webClient) {
        this.bookShelfRepository = bookShelfRepository;
        this.webClient = webClient;
    }

    @Transactional
    public @NotNull BookShelfResponse create(BookShelfRequest bookShelfRequest) {
        existUser2(bookShelfRequest.getUserId());
        return convertBookShelfResponse(save(convertBookShelf(bookShelfRequest)));
    }

    @Transactional
    public @NotNull BookShelfResponse update(String id, BookShelfRequest bookShelfRequest) {
        BookShelf existingBookShelf = bookShelfRepository.findById(id).orElseThrow(() -> new BooKShelfNotFoundException(id));
        existUser(bookShelfRequest.getUserId());
        BeanUtils.copyProperties(bookShelfRequest, existingBookShelf);
        return convertBookShelfResponse(save(existingBookShelf));
    }

    public BookShelfResponse getById(String id) {
        BookShelf existingBookShelf =  bookShelfRepository.findById(id).orElseThrow(() -> new BooKShelfNotFoundException(id));
                return  convertBookShelfResponse(existingBookShelf);
    }

    public List<BookShelfResponse> getByIds(List<String> ids) {
        return bookShelfRepository.findAllByIdIs(ids).stream()
                .map(this::convertBookShelfResponse)
                .toList();
    }

    @Transactional
    public String delete(String id) {
        BookShelf bookShelf = bookShelfRepository.findById(id).orElseThrow(() -> new BooKShelfNotFoundException(id));
        bookShelf.setDeleted(true);
        bookShelfRepository.save(bookShelf);

        return bookShelf.getId();
    }
    private @NotNull BookShelf convertBookShelf(BookShelfRequest bookShelfRequest) {
        var bookShelf = new BookShelf();
        BeanUtils.copyProperties(bookShelfRequest, bookShelf);
        return bookShelf;
    }

    private @NotNull BookShelfResponse convertBookShelfResponse(BookShelf bookShelf) {
        var bookShelfResponse = new BookShelfResponse();
        BeanUtils.copyProperties(bookShelf, bookShelfResponse);
        return bookShelfResponse;
    }

    private @NotNull BookShelf save(BookShelf bookShelf) {
        return bookShelfRepository.save(bookShelf);
    }

    private void existUser(Integer userId) {
        Boolean existUser = webClient.get().uri("http://localhost:8083/api/users/").retrieve().bodyToMono(Boolean.class).block();
        if (Boolean.FALSE.equals(existUser)) {
            throw new UserNotFoundException(userId);
        }
    }
    private void existUser2(Integer userId) {
        Boolean existUser = webClient.get().uri(uriBuilder -> uriBuilder
                .path("/users")
                .queryParam("userId", 1).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.FALSE.equals(existUser)) {
            throw new UserNotFoundException(userId);
        }
    }

}
