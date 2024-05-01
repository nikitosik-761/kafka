package org.notification.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Notifications")
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notifications_id_seq",
            sequenceName = "notifications_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notifications_id_seq"
    )
    private Long id;

    private String message;

    private LocalDateTime timestamp;

    private String service;

}
