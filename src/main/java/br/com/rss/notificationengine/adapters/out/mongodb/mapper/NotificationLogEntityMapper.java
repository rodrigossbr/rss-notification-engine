package br.com.rss.notificationengine.adapters.out.mongodb.mapper;

import br.com.rss.notificationengine.adapters.out.mongodb.entity.NotificationLogEntity;
import br.com.rss.notificationengine.adapters.out.mongodb.entity.NotificationLogEntity.NotificationContentEntity;
import br.com.rss.notificationengine.core.domain.NotificationContent;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.mapper.GenericMapper;
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
                .id(input.id())
                .sendId(input.sendId())
                .destination(input.destination())
                .channel(input.channel())
                .content(NotificationContentEntity.builder()
                        .body(input.content().body())
                        .attachmentsBase64(input.content().attachmentsBase64())
                        .build())
                .status(input.status())
                .build();
    }

    @Override
    public NotificationLog mapBack(NotificationLogEntity entity) {

        if (isNull(entity)) {
            return null;
        }

        return NotificationLog.builder()
                .id(entity.getId())
                .sendId(entity.getSendId())
                .destination(entity.getDestination())
                .channel(entity.getChannel())
                .content(NotificationContent.builder()
                        .body(entity.getContent().getBody())
                        .attachmentsBase64(entity.getContent().getAttachmentsBase64())
                        .build())
                .status(entity.getStatus())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
