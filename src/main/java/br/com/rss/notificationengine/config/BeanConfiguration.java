//package br.com.rss.notificationengine.config;
//
//import br.com.rss.notificationengine.core.ports.out.SendMessageBrokerPort;
//import br.com.rss.notificationengine.core.ports.in.NotificationLogPort;
//import br.com.rss.notificationengine.core.usecase.SendNotificationUseCase;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class BeanConfiguration {
//
//    @Bean
//    public SendNotificationUseCase sendNotificationUseCase(
//            NotificationLogPort repositoryPort,
//            SendMessageBrokerPort brokerPort) {
//
//        return new SendNotificationUseCase(repositoryPort, brokerPort);
//    }
//}