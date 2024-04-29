package org.bookpersistence.services;

import lombok.RequiredArgsConstructor;
import org.bookpersistence.domain.Book;
import org.bookpersistence.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> listBooks(Pageable pagable) {
        return bookRepository.findAll(pagable);
    }
}
