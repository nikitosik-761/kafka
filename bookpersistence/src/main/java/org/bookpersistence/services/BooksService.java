package org.bookpersistence.services;


import org.bookpersistence.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BooksService {
    Book save(Book book);

    Page<Book> listBooks(Pageable pagable);
}
