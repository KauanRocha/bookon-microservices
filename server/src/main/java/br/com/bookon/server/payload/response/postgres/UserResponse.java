package br.com.bookon.server.payload.response.postgres;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bookon.server.models.postgres.User;

public class UserResponse {

    private User user;
    
    private Integer distance;

    public UserResponse(User user) {
        this.user = user;
    }

	public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Set<RoleResponse> getRoles() {
        return user.getRoles().stream().map(RoleResponse::new).collect(Collectors.toSet());
    }
    
    public List<BookResponse> getBooks() {
		return user.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
    
}
