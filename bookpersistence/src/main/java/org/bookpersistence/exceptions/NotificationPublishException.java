package org.bookpersistence.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bookpersistence.domain.Notification;

public class NotificationPublishException extends RuntimeException{
    public NotificationPublishException(String message, Throwable ex, Notification notification) {
    }
}
