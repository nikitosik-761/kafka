package org.authorpersistence.exceptions;


import org.authorpersistence.domain.Notification;

public class NotificationPublishException extends RuntimeException{
    public NotificationPublishException(String message, Throwable ex, Notification notification) {
    }
}
