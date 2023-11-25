package br.com.bookon.loan.models;

import br.com.bookon.loan.enumerations.LoanStatusEnum;
import br.com.bookon.server.models.mongo.BookMongo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "loans")
public class Loan {
    @Id
    private String id;
    
    @Field("borrowerUser")
    private UserMongo borrowerUser;

    @Field("lenderUser")
    private UserMongo lenderUser;

    @Field("book")
    private BookMongo book;

    @Field("status")
    private LoanStatusEnum status;

    @Field("startDate")
    private LocalDateTime startDate;

    @Field("returnDate")
    private LocalDateTime returnDate;
    
    @Field("createAt")
    private LocalDateTime createAt = LocalDateTime.now();


	public BookMongo getBook() {
		return book;
	}

	public void setBook(BookMongo book) {
		this.book = book;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserMongo getBorrowerUser() {
		return borrowerUser;
	}

	public void setBorrowerUser(UserMongo borrowerUser) {
		this.borrowerUser = borrowerUser;
	}

	public UserMongo getLenderUser() {
		return lenderUser;
	}

	public void setLenderUser(UserMongo lenderUser) {
		this.lenderUser = lenderUser;
	}

	public LoanStatusEnum getStatus() {
		return status;
	}

	public void setStatus(LoanStatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Loan(String id, UserMongo borrowerUser, UserMongo lenderUser, BookMongo book, LocalDateTime startDate,
				LocalDateTime returnDate, LocalDateTime createAt, LoanStatusEnum status) {
		super();
		this.id = id;
		this.borrowerUser = borrowerUser;
		this.lenderUser = lenderUser;
		this.book = book;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.status = status;
		this.createAt = createAt;
	}

	public Loan() {
	}
}
