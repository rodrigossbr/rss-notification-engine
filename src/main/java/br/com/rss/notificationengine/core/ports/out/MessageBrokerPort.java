package br.com.rss.notificationengine.core.ports.out;

import br.com.rss.notificationengine.core.domain.NotificationLog;

public interface MessageBrokerPort {
    void send(NotificationLog notificationLog);
}