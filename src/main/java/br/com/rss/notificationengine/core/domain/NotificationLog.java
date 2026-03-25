package br.com.rss.notificationengine.core.domain;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnun;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnun;
import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record NotificationLog(
        String id,
        String sendId,
        String destination,
        NotificationEnun channel,
        NotificationContent content,
        NotificationTemplate template,
        NotificationStatusEnun status,
        Instant createdAt,
        Instant updatedAt
) {
    public NotificationLog withId(String id) {
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
