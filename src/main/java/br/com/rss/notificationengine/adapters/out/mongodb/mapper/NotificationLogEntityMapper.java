package br.com.rss.notificationengine.adapters.out.mongodb.mapper;

import br.com.rss.notificationengine.adapters.out.mongodb.entity.NotificationContentEntity;
import br.com.rss.notificationengine.adapters.out.mongodb.entity.NotificationLogEntity;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.mappers.GenericMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class NotificationLogEntityMapper implements GenericMapper<NotificationLog, NotificationLogEntity> {

    @Override
    public NotificationLogEntity map(NotificationLog input) {

        if (isNull(input)) {
            return null;
        }

        return NotificationLogEntity.builder()
                .sendId(input.sendId())
                .destination(input.destination())
                .channel(input.channel())
                .content(NotificationContentEntity.builder()
                        .body(input.content().body())
                        .attachmentsBase64(input.content().attachmentsBase64())
                        .build())
                .status(input.status())
                .updatedAt(input.updatedAt())
                .createdAt(input.createdAt())
                .build();
    }
}
