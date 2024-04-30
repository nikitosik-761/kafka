package org.bookpersistence.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookpersistence.domain.Book;
import org.bookpersistence.domain.Notification;
import org.bookpersistence.exceptions.InvalidMessageException;
import org.bookpersistence.services.BooksService;
import org.bookpersistence.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookPublishedListener {
    private final ObjectMapper objectMapper;

    private final BooksService booksService;

    private final NotificationService notificationService;


    @KafkaListener(topics = "books.published")
    public String listens(String in) {
        log.info("Received Book: {}", in);
        try {
            final Map<String, Object> payload = readJsonAsMap(in);

            final Book book = bookFromPayload(payload);
            final Book savedBook = booksService.save(book);

            final String message = String.format(
                    "Book '%s' [%s] persisted!",
                    savedBook.getTitle(),
                    savedBook.getIsbn()
            );
            notificationService.publishNotification(
                    Notification.builder()
                            .message(message)
                            .timestamp(LocalDateTime.now())
                            .service("book-persistence")
                            .build());

        } catch(final InvalidMessageException ex) {
            log.error("Invalid message received: {}", in);
        }


        return in;
    }

    private Map<String, Object> readJsonAsMap(String json) {
        try{
            final TypeReference<HashMap<String,Object>> typeRef = new TypeReference<>() {};
            return objectMapper.readValue(json, typeRef);
        } catch(JsonProcessingException ex) {
            throw new InvalidMessageException();
        }
    }

    private Book bookFromPayload(final Map<String, Object> payload) {
        final Integer authorId = (Integer)((HashMap<String, Object>)payload.get("author")).get("id");
        return Book.builder()
                .isbn(payload.get("isbn").toString())
                .title(payload.get("title").toString())
                .author(authorId.longValue())
                .build();
    }


}
