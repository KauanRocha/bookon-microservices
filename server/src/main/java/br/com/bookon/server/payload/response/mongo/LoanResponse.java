package br.com.bookon.server.payload.response.mongo;

import java.time.LocalDateTime;

import br.com.bookon.server.models.mongo.Loan;

public class LoanResponse {

	private Loan loan;
	
	public LoanResponse(Loan loan) {
		this.loan= loan;
	}
	
	public String getId() {
		return loan.getId();
	}
	
	public UserResponse getBorrowerUser() {
		return new UserResponse(loan.getBorrowerUser());
	}

	public UserResponse getLenderUser() {
		return new UserResponse(loan.getLenderUser());
	}

	public BookResponse getBook() {
		return new BookResponse(loan.getBook());
	}

	public LocalDateTime getStartDate() {
		return loan.getStartDate();
	}
    
}
