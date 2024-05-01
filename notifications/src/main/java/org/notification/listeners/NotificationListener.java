package org.notification.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.notification.domain.Notification;
import org.notification.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final ObjectMapper objectMapper;

    private final NotificationService notificationService;


    @KafkaListener(topics = "notification.created")
    public String listens(final String in) {
        log.info("Received Notification: {}", in);
        try {
            final Notification notification = objectMapper.readValue(in, Notification.class);

            final Notification savedNotification = notificationService.save(notification);

            log.info("Notification '{}' persisted!", savedNotification.getTimestamp().toString());

        } catch(final JsonProcessingException ex) {
            log.error("Invalid message received: {}", in);
        }

        return in;
    }
}
