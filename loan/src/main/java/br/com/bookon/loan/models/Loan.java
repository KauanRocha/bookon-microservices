package br.com.bookon.loan.models;

import br.com.bookon.loan.enums.LoanStatusEnum;
import br.com.bookon.loan.payloads.response.BookResponse;
import br.com.bookon.loan.payloads.response.UserResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Document(collection = "loans")
public class Loan {
    @Id
    private String id;

    @Field("borrower")
    private UserResponse borrower;

    @Field("lender")
    private UserResponse lender;

    @Field("book")
    private BookResponse book;

    @Field("status")
    private LoanStatusEnum status;

    @Field("startDate")
    private LocalDateTime startDate;

    @Field("returnDate")
    private LocalDateTime returnDate;

    @Field("createAt")
    private LocalDateTime createAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserResponse getBorrower() {
        return borrower;
    }

    public void setBorrower(UserResponse borrower) {
        this.borrower = borrower;
    }

    public UserResponse getLender() {
        return lender;
    }

    public void setLender(UserResponse lender) {
        this.lender = lender;
    }

    public BookResponse getBook() {
        return book;
    }

    public void setBook(BookResponse book) {
        this.book = book;
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
}