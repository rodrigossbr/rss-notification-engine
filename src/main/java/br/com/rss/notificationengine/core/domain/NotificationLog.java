package br.com.rss.notificationengine.core.domain;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnun;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnun;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record NotificationLog(
        UUID id,
        UUID sendId,
        String destination,
        NotificationEnun channel,
        NotificationContent content,
        NotificationTemplate template,
        NotificationStatusEnun status,
        Instant createdAt,
        Instant updatedAt
) {
    public NotificationLog withId(UUID id) {
        return new NotificationLog(
                id,
                sendId,
                destination,
                channel,
                content,
                template,
                status,
                createdAt,
                updatedAt
        );
    }
}
