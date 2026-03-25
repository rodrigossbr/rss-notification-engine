package br.com.rss.notificationengine.adapters.out.mongodb.entity;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnun;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "notification_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLogEntity {

    @Id
    private String id;

    @Indexed
    private String sendId;

    private String destination;

    private NotificationEnun channel;

    private NotificationContentEntity content;

    private NotificationStatusEnun status;

    private Instant createdAt;

    private Instant updatedAt;
}
