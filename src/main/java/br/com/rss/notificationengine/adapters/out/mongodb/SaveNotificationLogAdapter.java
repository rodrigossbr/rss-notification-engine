package br.com.rss.notificationengine.adapters.out.mongodb;

import br.com.rss.notificationengine.adapters.out.mongodb.mapper.NotificationLogEntityMapper;
import br.com.rss.notificationengine.adapters.out.mongodb.repository.NotificationRepository;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.out.NotificationLogOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SaveNotificationLogAdapter implements NotificationLogOutputPort {

    private final NotificationRepository repository;
    private final NotificationLogEntityMapper mapper;

    @Override
    public List<NotificationLog> findAllBySendId(UUID sendId) {

        var entities = repository.findBySendId(sendId);

        return mapper.toListBack(entities);
    }

    @Override
    public NotificationLog save(NotificationLog domainLog) {

        var entity = mapper.map(domainLog);

        var saved = repository.save(entity);

        return domainLog.withId(saved.getId());
    }
}