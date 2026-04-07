package br.com.rss.notificationengine.adapters.out.mongodb.entity;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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

    private NotificationEnum channel;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
