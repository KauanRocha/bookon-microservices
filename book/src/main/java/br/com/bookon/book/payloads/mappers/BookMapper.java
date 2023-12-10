package br.com.bookon.book.payloads.mappers;

import br.com.bookon.book.models.Book;
import br.com.bookon.book.payloads.request.BookRequest;
import br.com.bookon.book.payloads.response.BookResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BookResponse convertToBookResponse(Book book) {
        return modelMapper.map(book, BookResponse.class);
    }

    public Book convertToBook(BookRequest bookRequest) {
        return modelMapper.map(bookRequest, Book.class);
    }
}

