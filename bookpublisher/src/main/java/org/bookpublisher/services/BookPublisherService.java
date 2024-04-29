package org.bookpublisher.services;

import org.bookpublisher.domain.Book;


public interface BookPublisherService {
    default void publish(Book book){

    }
}
