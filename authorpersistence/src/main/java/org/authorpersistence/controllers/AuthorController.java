package org.authorpersistence.controllers;

import lombok.RequiredArgsConstructor;
import org.authorpersistence.domain.Author;
import org.authorpersistence.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    @GetMapping
    public Page<Author> listAuthors(final Pageable pagable) {
        return authorService.listAuthors(pagable);
    }
}
