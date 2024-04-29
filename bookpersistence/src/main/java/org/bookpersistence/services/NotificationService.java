package org.bookpersistence.services;

import org.bookpersistence.domain.Notification;

public interface NotificationService {
    void publishNotification(Notification notification);
}
