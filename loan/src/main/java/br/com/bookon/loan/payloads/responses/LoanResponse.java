package br.com.bookon.loan.payloads.responses;

import br.com.bookon.server.models.mongo.Loan;

import java.time.LocalDateTime;

public class LoanResponse {

	private Loan loan;
	
	public LoanResponse(Loan loan) {
		this.loan= loan;
	}

	public LoanResponse(br.com.bookon.loan.models.Loan loan) {
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
