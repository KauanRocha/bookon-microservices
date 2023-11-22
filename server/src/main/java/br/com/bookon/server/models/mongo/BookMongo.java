package br.com.bookon.server.models.mongo;

public class BookMongo {

	private Long id;
	private String title;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public BookMongo(br.com.bookon.server.models.postgres.Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
	}
	
	public BookMongo() {
	}
	
}
