package br.com.bookon.book.payloads.request;

import br.com.bookon.book.enums.BookCategoryEnum;

public record BookRequest(String title, String author, BookCategoryEnum category) {
}
