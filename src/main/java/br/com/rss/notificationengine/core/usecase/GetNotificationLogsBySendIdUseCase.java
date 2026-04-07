package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.in.GetNotificationLogsBySendIdInputPort;
import br.com.rss.notificationengine.core.ports.out.NotificationLogOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class GetNotificationLogsBySendIdUseCase implements GetNotificationLogsBySendIdInputPort {

    private final NotificationLogOutputPort notificationLogOutputPort;

    @Override
    public List<NotificationLog> execute(UUID sendId) {
        log.info("Buscando logs de notificação para o sendId: {}", sendId);
        return notificationLogOutputPort.findAllBySendId(sendId);
    }
}
