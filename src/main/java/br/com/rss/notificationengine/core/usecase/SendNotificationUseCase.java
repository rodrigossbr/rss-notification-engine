package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.out.MessageBrokerPort;
import br.com.rss.notificationengine.core.ports.out.NotificationRepositoryPort;

public class SendNotificationUseCase {

    private final NotificationRepositoryPort repository;
    private final MessageBrokerPort broker;

    public SendNotificationUseCase(NotificationRepositoryPort repository, MessageBrokerPort broker) {
        this.repository = repository;
        this.broker = broker;
    }

    public void execute(NotificationLog notification) {
        // 1. Regra de Negócio: Poderíamos validar o canal aqui
        // 2. Persistência: Salva no MongoDB como PENDING
        NotificationLog savedNotification = repository.save(notification);

        // 3. Mensageria: Envia para o RabbitMQ para ser processado de fato
        broker.send(savedNotification);

        System.out.println("Notificação orquestrada com sucesso: " + savedNotification.id());
    }
}