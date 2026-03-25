package br.com.rss.notificationengine.adapters.out.rabbitmq;

import br.com.rss.notificationengine.config.RabbitConfig;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.out.SendMessageBrokerOutputPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQAdapter implements SendMessageBrokerOutputPort {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(NotificationLog notification) {
        String routingKey = "notification." + notification.channel();
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, routingKey, notification);
    }
}