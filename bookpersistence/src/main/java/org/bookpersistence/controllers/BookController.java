package org.bookpersistence.controllers;

import lombok.RequiredArgsConstructor;
import org.bookpersistence.domain.Book;
import org.bookpersistence.services.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BooksService booksService;

    @GetMapping("/books")
    public Page<Book> listBooks(final Pageable pagable) {
        return booksService.listBooks(pagable);
    }
}
