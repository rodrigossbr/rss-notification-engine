package br.com.rss.notificationengine.core.ports.out;

import br.com.rss.notificationengine.core.domain.NotificationLog;

import java.util.List;
import java.util.UUID;

public interface NotificationLogOutputPort {

    List<NotificationLog> findAllBySendId(UUID sendId);

    NotificationLog save(NotificationLog notificationLog);
}