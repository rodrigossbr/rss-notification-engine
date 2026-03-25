package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.NotificationLog;

import java.util.List;
import java.util.UUID;

public interface GetNotificationLogsBySendIdInputPort {
    List<NotificationLog> execute(UUID sendId);
}
