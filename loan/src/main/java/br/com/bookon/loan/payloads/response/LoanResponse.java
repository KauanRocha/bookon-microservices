package br.com.bookon.loan.payloads.response;

import jakarta.validation.Valid;

public record LoanResponse(@Valid String id, @Valid BookResponse book, @Valid UserResponse lender, @Valid UserResponse borrower) {
}