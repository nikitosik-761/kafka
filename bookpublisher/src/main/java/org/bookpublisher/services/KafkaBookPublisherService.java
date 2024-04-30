package org.bookpublisher.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookpublisher.domain.Book;
import org.bookpublisher.exceptions.BookPublishException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaBookPublisherService implements BookPublisherService{

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${my.kafka.topic}")
    private String topic;


    @Override
    public void publish(Book book) {
        try {
            final String payload = objectMapper.writeValueAsString(book);
            kafkaTemplate.send(topic, payload)
                    .whenComplete(
                            (result, ex) -> {
                                if (ex == null){
                                    log.info(
                                            "message: {} was send with offset {}",
                                            payload,
                                            result.getRecordMetadata().offset()
                                    );
                                }else {
                                    log.error("message: {} was not send", payload, ex);
                                }
                            }
                    );
        } catch (final JsonProcessingException ex) {
            throw new BookPublishException("Unable to publish book", ex, book);
        } catch (Exception ex){
            log.error("Error with sending book_id: {}", book.getId(), ex);
        }
    }
}
