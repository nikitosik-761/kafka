package org.notification.services;

import org.notification.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    Notification save(Notification notification);

    Page<Notification> listNotifications(Pageable pageable);
}
