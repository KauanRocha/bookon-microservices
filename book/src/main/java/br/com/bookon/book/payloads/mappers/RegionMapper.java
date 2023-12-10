package br.com.bookon.book.payloads.mappers;

import br.com.bookon.book.payloads.response.BookResponse;
import br.com.bookon.book.payloads.response.RegionWithBookRosponse;
import br.com.bookon.book.payloads.response.RegionWithUsersResponse;
import br.com.bookon.book.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private static BookRepository bookRepository;

    public RegionWithBookRosponse convertToRegionWithBook(RegionWithUsersResponse regionWithUsers) {
        RegionWithBookRosponse regionWithBook = modelMapper.map(regionWithUsers, RegionWithBookRosponse.class);

        List<BookResponse> BooksFromUsers = bookRepository.findBooksByUserIdIn(regionWithUsers.userIds())
                .stream()
                .map(bookMapper::convertToBookResponse)
                .toList();

        regionWithBook.setBooks(BooksFromUsers);

        return regionWithBook;
    }
}
