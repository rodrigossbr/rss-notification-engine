package br.com.rss.notificationengine.adapters.out.mongodb.repository;

import br.com.rss.notificationengine.adapters.out.mongodb.entity.NotificationLogEntity;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationLogEntity, String> {

    List<NotificationLogEntity> findBySendId(String sendId);
}