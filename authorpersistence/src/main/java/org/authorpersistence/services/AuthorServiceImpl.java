package org.authorpersistence.services;

import lombok.RequiredArgsConstructor;
import org.authorpersistence.domain.Author;
import org.authorpersistence.repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Page<Author> listAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
