package br.com.bookon.loan.payloads.request;

import jakarta.validation.Valid;

public record LoanRequest(@Valid Long lender,@Valid Long borrower, @Valid Long book) {

}
