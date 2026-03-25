package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.NotificationLog;

import java.util.List;

public interface NotificationLogPort {

    List<NotificationLog> findAllBySendId(String sendId);

    NotificationLog save(NotificationLog notificationLog);
}