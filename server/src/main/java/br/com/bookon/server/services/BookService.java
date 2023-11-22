package br.com.bookon.server.services;

import br.com.bookon.server.exceptions.NotFoundException;
import br.com.bookon.server.models.postgres.Book;
import br.com.bookon.server.models.postgres.User;
import br.com.bookon.server.payload.request.postgres.BookRequest;
import br.com.bookon.server.payload.response.postgres.BookResponse;
import br.com.bookon.server.payload.response.postgres.RegionWithBookRosponse;
import br.com.bookon.server.payload.response.postgres.RegionWithUsersRosponse;
import br.com.bookon.server.repositories.postgres.BookRepository;
import br.com.bookon.server.repositories.postgres.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

	@Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    

    public BookResponse createBook(BookRequest bookRequest, Integer userId){

        Book book = bookRequest.build();
		
		Optional<User> user = userRepository.findById(userId);
		  if (user.isEmpty()) { 
			  new NotFoundException("user-not-found-by-id: " + userId);
		  }
		  book.setUser(user.get());
		  
        return new BookResponse(bookRepository.save(book));
    }

    public List<BookResponse> getAllBooks() {
    	List<Book> books = bookRepository.findAll();
    	List<BookResponse> bookResponses = books.stream()
	            .map(BookResponse::new)
	            .collect(Collectors.toList());
    	
        return bookResponses;
    }

    public List<Book> getBookByUserId(Integer userId) {
        return bookRepository.findBookByUserId(userId);
    }

    @Transactional
    public BookResponse updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());

        return new BookResponse(bookRepository.save(existingBook));
    }

    @Transactional
    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findBookById(id);
        bookRepository.delete(existingBook);
    }
    
    public List<RegionWithBookRosponse> findRegionsWithNearbyBooksByUserGeolocation(Integer userFinderId) {
    	List<RegionWithUsersRosponse> regionsWithUsers = userService.findRegionsWithNearbyUsersByUserGeolocation(userFinderId);
        List<RegionWithBookRosponse> regionsWithBooks = regionsWithUsers.stream()
            .map(RegionWithUsersRosponse::toRegionWithBookRosponse)
            .collect(Collectors.toList());
    	
    	return regionsWithBooks;
    }
    
    public List<RegionWithBookRosponse> findRegionsWithNearbyBooksByAddress(Integer userFinderId, String address) {
    	List<RegionWithUsersRosponse> regionsWithUsers = userService.findRegionsWithNearbyUsersByAdress(userFinderId, address);
        List<RegionWithBookRosponse> regionsWithBooks = regionsWithUsers.stream()
            .map(RegionWithUsersRosponse::toRegionWithBookRosponse)
            .collect(Collectors.toList());
    	
    	return regionsWithBooks;
    }
    
}
