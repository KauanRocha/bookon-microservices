package br.com.bookon.loan.payloads.request;

import jakarta.validation.constraints.NotNull;

public record LoanRequest(@NotNull Integer lenderId, @NotNull Integer borrowerId, @NotNull Integer bookId) {

}
