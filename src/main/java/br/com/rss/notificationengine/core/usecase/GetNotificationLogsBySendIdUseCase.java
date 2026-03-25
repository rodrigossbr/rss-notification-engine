package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.in.GetNotificationLogsBySendIdInputPort;
import br.com.rss.notificationengine.core.ports.out.NotificationLogOutputPort;

import java.util.List;
import java.util.UUID;

public class GetNotificationLogsBySendIdUseCase implements GetNotificationLogsBySendIdInputPort {

    private final NotificationLogOutputPort notificationLogOutputPort;

    public GetNotificationLogsBySendIdUseCase(NotificationLogOutputPort notificationLogPort) {
        this.notificationLogOutputPort = notificationLogPort;
    }

    @Override
    public List<NotificationLog> execute(UUID sendId) {
        return notificationLogOutputPort.findAllBySendId(sendId);
    }
}
