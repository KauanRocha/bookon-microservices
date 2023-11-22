package br.com.bookon.server.payload.response.mongo;

import br.com.bookon.server.models.mongo.UserMongo;

public class UserResponse {

	private UserMongo user;

	public Integer getId() {
		return user.getId();
	}
	
	public String getName() {
		return user.getName();
	}

	public UserResponse(UserMongo user) {
		this.user = user;
	}
	
}
