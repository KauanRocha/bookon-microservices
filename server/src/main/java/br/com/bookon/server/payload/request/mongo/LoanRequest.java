package br.com.bookon.server.payload.request.mongo;

import jakarta.validation.constraints.NotNull;

public class LoanRequest {

	@NotNull
    private Integer lenderId;

    @NotNull
    private Long bookId;

	public Integer getLenderId() {
		return lenderId;
	}
	
	public Long getBookId() {
		return bookId;
	}

}
