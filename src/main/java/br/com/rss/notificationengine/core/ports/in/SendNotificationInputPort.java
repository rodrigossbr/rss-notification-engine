package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.NotificationLog;

public interface SendNotificationInputPort {
    void execute(NotificationLog notification);
}
