package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.in.NotificationLogPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNotificationLogsBySendIdUseCase {

    private final NotificationLogPort notificationLogPort;

    public List<NotificationLog> execute(String sendId) {
        return notificationLogPort.findAllBySendId(sendId);
    }
}
