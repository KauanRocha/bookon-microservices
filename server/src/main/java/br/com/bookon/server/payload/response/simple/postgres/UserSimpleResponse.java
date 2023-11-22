package br.com.bookon.server.payload.response.simple.postgres;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bookon.server.models.postgres.User;
import br.com.bookon.server.payload.response.postgres.BookResponse;

public class UserSimpleResponse {

    private User user;
    
    private String distance;
    

	public UserSimpleResponse(User user, String distance) {
		super();
		this.user = user;
		this.distance = distance;
	}

	public String getUsername() {
        return user.getUsername();
    }
    
    public List<BookResponse> getBooks() {
		return user.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
    
}
