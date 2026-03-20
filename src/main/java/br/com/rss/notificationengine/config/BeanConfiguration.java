package br.com.rss.notificationengine.config;

import br.com.rss.notificationengine.core.ports.out.MessageBrokerPort;
import br.com.rss.notificationengine.core.ports.out.NotificationRepositoryPort;
import br.com.rss.notificationengine.core.usecase.SendNotificationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public SendNotificationUseCase sendNotificationUseCase(
            NotificationRepositoryPort repositoryPort,
            MessageBrokerPort brokerPort) {

        // Aqui você instancia o seu Use Case injetando as interfaces (ports)
        // O Spring vai procurar quem implementa essas interfaces nos Adapters automaticamente
        return new SendNotificationUseCase(repositoryPort, brokerPort);
    }
}