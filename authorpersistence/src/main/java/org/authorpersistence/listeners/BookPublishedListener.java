package org.authorpersistence.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authorpersistence.domain.Author;
import org.authorpersistence.domain.Notification;
import org.authorpersistence.exceptions.InvalidMessageException;
import org.authorpersistence.services.AuthorService;
import org.authorpersistence.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookPublishedListener {

    private final ObjectMapper objectMapper;

    private final AuthorService authorService;

    private final NotificationService notificationService;

    @KafkaListener(topics = "books.published")
    public String listens(final String in) {
        log.info("Received Book: {}", in);
        try {
            final Map<String, Object> payload = readJsonAsMap(in);

            final Author author = authorFromPayload(payload);
            final Author savedAuthor = authorService.save(author);

            final String message = String.format(
                    "Author '%s' [%d] persisted!",
                    savedAuthor.getName(),
                    savedAuthor.getId()
            );
            notificationService.publishNotification(
                    Notification.builder()
                            .message(message)
                            .timestamp(LocalDateTime.now())
                            .service("author-persistence")
                            .build());

        } catch(final InvalidMessageException ex) {
            log.error("Invalid message received: {}", in);
        }


        return in;
    }

    private Map<String, Object> readJsonAsMap(final String json) {
        try{
            final TypeReference<HashMap<String,Object>> typeRef = new TypeReference<>() {};
            return objectMapper.readValue(json, typeRef);
        } catch(JsonProcessingException ex) {
            throw new InvalidMessageException();
        }
    }


    private Author authorFromPayload(final Map<String, Object> payload) {
        final Map<String, Object> authorMap = (HashMap<String, Object>) payload.get("author");
        return Author.builder()
                .id(((Integer)authorMap.get("id")).longValue())
                .name(authorMap.get("name").toString())
                .age((Integer)authorMap.get("age"))
                .build();
    }


}
