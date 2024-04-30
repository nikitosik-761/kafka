package org.bookpersistence.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookpersistence.domain.Notification;
import org.bookpersistence.exceptions.NotificationPublishException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaNotificationService implements NotificationService {

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${my.kafka.notification.topic}")
    private  String topic;

    @Override
    public void publishNotification(Notification notification) {
        try {
            final String payload = objectMapper.writeValueAsString(notification);
            kafkaTemplate.send(topic, payload).whenComplete(
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
        } catch(final JsonProcessingException ex) {
            throw new NotificationPublishException("Unable to publish notification", ex, notification);
        } catch (Exception ex){
            log.error("Error with sending book_id: {}", notification, ex);
         }
    }
}
