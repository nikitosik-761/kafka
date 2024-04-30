package org.bookpersistence.controllers;

import lombok.RequiredArgsConstructor;
import org.bookpersistence.domain.Book;
import org.bookpersistence.services.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BooksService booksService;

    @GetMapping
    public Page<Book> listBooks(Pageable pagable) {
        return booksService.listBooks(pagable);
    }
}
