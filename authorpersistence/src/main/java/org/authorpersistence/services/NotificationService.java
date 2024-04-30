package org.authorpersistence.services;

import org.authorpersistence.domain.Notification;

public interface NotificationService {
    void publishNotification(Notification notification);
}
