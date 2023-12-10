package br.com.bookon.book.payloads.response;

import br.com.bookon.book.enums.BookCategoryEnum;

public record BookResponse(String title, String author, BookCategoryEnum category, Long userId) {
}
