package br.com.bookon.loan.models;

import br.com.bookon.server.models.postgres.User;

public class UserMongo {

	private Integer id;
	private String name;
	private String email;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserMongo(User user) {
		this.id = user.getId();
		this.name = user.getUsername();
		this.email = user.getEmail();
		
	}
	
	public UserMongo() {
	}
	
}