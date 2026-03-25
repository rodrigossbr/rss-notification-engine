package br.com.rss.notificationengine.adapters.out.mongodb.entity;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnun;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "templates")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateEntity {
    @Id
    private UUID id;

    @Indexed(unique = true)
    private String templateKey;

    private String name;

    private String content;

    private NotificationEnun channel;

    private Instant createdAt;

    private Instant updatedAt;
}
