package br.com.rss.notificationengine.adapters.out.mongodb.entity;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnun;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnun;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "notification_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLogEntity {

    @Id
    private UUID id;

    @Indexed
    private UUID sendId;

    private String destination;

    private NotificationEnun channel;

    private NotificationContentEntity content;

    private NotificationStatusEnun status;

    private Instant createdAt;

    private Instant updatedAt;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationContentEntity {

        private String body;

        private List<String> attachmentsBase64;
    }
}
