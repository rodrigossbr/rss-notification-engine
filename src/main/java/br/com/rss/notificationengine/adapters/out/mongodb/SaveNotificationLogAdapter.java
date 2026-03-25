package br.com.rss.notificationengine.adapters.out.mongodb;

import br.com.rss.notificationengine.adapters.out.mongodb.mapper.NotificationLogEntityMapper;
import br.com.rss.notificationengine.adapters.out.mongodb.repository.NotificationRepository;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.in.NotificationLogPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveNotificationLogAdapter implements NotificationLogPort {

    private final NotificationRepository repository;
    private final NotificationLogEntityMapper mapper;

    @Override
    public List<NotificationLog> findAllBySendId(String sendId) {

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