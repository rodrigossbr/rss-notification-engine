package br.com.rss.notificationengine.config;

import br.com.rss.notificationengine.core.ports.in.*;
import br.com.rss.notificationengine.core.ports.out.NotificationLogOutputPort;
import br.com.rss.notificationengine.core.ports.out.SendMessageBrokerOutputPort;
import br.com.rss.notificationengine.core.ports.out.TemplateEngineOutputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;
import br.com.rss.notificationengine.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateTemplateInputPort createTemplateUseCase(TemplatePersistenceOutputPort templatePersistencePort) {
        return new CreateTemplateUseCase(templatePersistencePort);
    }

    @Bean
    public GetAllTemplatesInputPort getAllTemplatesUseCase(TemplatePersistenceOutputPort templatePersistencePort) {
        return new GetAllTemplatesUseCase(templatePersistencePort);
    }

    @Bean
    public GetTemplateInputPort getTemplateUseCase(TemplatePersistenceOutputPort templatePersistencePort) {
        return new GetTemplateUseCase(templatePersistencePort);
    }

    @Bean
    public UpdateTemplateInputPort updateTemplateUseCase(TemplatePersistenceOutputPort templatePersistencePort) {
        return new UpdateTemplateUseCase(templatePersistencePort);
    }

    @Bean
    public GetNotificationLogsBySendIdInputPort getNotificationLogsBySendIdUseCase(NotificationLogOutputPort notificationLogPersistencePort) {
        return new GetNotificationLogsBySendIdUseCase(notificationLogPersistencePort);
    }

    @Bean
    public SendNotificationInputPort sendNotificationUseCase(
            NotificationLogOutputPort repositoryPort,
            SendMessageBrokerOutputPort brokerPort,
            TemplateEngineOutputPort templateEnginePort,
            TemplatePersistenceOutputPort templatePersistencePort) {

        return new SendNotificationUseCase(repositoryPort, brokerPort, templateEnginePort, templatePersistencePort);
    }
}