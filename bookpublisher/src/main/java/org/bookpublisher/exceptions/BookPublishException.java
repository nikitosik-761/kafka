package org.bookpublisher.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bookpublisher.domain.Book;

public class BookPublishException extends RuntimeException{
    public BookPublishException(String message) {
        super(message);
    }

    public BookPublishException(String message, Throwable ex, Book book) {

    }
}
