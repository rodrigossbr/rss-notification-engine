package br.com.rss.notificationengine.adapters.in.rest.mappers;

import br.com.rss.notificationengine.adapters.in.rest.response.NotificationLogResponse;
import br.com.rss.notificationengine.adapters.in.rest.response.NotificationLogResponse.NotificationContentResponse;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.mapper.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Component
public class NotificationLogResponseMapper implements GenericMapper<NotificationLog, NotificationLogResponse> {

    @Override
    public NotificationLogResponse map(NotificationLog source) {

        if (isNull(source)) {
            return null;
        }

        return NotificationLogResponse.builder()
                .sendId(source.sendId())
                .destination(source.destination())
                .channel(source.channel())
                .status(source.status())
                .content(ofNullable(source.content()).map(content -> NotificationContentResponse.builder()
                                .body(content.body())
                                .attachmentsBase64(content.attachmentsBase64())
                                .build())
                        .orElse(null))
                .createdAt(source.createdAt())
                .updatedAt(source.updatedAt())
                .build();
    }
}
