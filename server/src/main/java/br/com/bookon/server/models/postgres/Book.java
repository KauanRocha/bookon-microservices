package br.com.bookon.server.models.postgres;

import jakarta.persistence.Entity;

import java.io.Serializable;

import br.com.bookon.server.enumerations.BookCategoryEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String author;
	private BookCategoryEnum category;

	@ManyToOne
	@JoinColumn(name = "user_id") 
	private User user;

	public Book() {
	}

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookCategoryEnum getCategory() {
		return category;
	}

	public void setCategory(BookCategoryEnum category) {
		this.category = category;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book(Long id, String title, String author, BookCategoryEnum category, User user) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.user = user;
	}
}
