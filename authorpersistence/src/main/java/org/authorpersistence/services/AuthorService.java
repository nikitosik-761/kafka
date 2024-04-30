package org.authorpersistence.services;

import org.authorpersistence.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Author save(Author author);
    Page<Author> listAuthors(Pageable pageable);
}
