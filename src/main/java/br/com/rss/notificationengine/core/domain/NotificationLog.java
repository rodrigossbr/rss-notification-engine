package br.com.rss.notificationengine.core.domain;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnum;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnum;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record NotificationLog(
        UUID id,
        UUID sendId,
        String destination,
        NotificationEnum channel,
        NotificationContent content,
        NotificationTemplate template,
        NotificationStatusEnum status,
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
