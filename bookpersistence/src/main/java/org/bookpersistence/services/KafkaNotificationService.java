package org.bookpersistence.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bookpersistence.config.KafkaConfigProps;
import org.bookpersistence.domain.Notification;
import org.bookpersistence.exceptions.NotificationPublishException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaNotificationService implements NotificationService {

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaConfigProps kafkaConfigProps;

    @Override
    public void publishNotification(Notification notification) {
        try {
            final String payload = objectMapper.writeValueAsString(notification);
            kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
        } catch(final JsonProcessingException ex) {
            throw new NotificationPublishException("Unable to publish notification", ex, notification);
        }
    }
}
