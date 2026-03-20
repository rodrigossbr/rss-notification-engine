package br.com.rss.notificationengine.adapters.out.mongodb;

import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.out.NotificationRepositoryPort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationLog, String>, NotificationRepositoryPort {
    // O Spring Data já implementa o save() que a Porta pede!
}