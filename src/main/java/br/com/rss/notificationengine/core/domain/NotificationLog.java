package br.com.rss.notificationengine.core.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "notifications")
public record NotificationLog(
        @Id String id,
        String userId,
        String destination,
        String channel,
        String status,
        Map<String, Object> payload,

        @CreatedDate
        LocalDateTime createdAt
) {}