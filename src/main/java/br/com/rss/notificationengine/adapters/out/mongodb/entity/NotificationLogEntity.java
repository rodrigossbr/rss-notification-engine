package br.com.rss.notificationengine.adapters.out.mongodb.entity;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnum;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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

    private NotificationEnum channel;

    private NotificationContentEntity content;

    private NotificationStatusEnum status;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
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
