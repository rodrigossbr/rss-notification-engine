package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnun;
import br.com.rss.notificationengine.core.ports.in.NotificationLogPort;
import br.com.rss.notificationengine.core.ports.out.SendMessageBrokerPort;
import br.com.rss.notificationengine.core.ports.out.TemplateEnginePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.util.Objects.isNull;


@Slf4j
@Service
@RequiredArgsConstructor
public class SendNotificationUseCase {

    private final NotificationLogPort saveNotificationLogPort;
    private final SendMessageBrokerPort sendMessageBrokerPort;
    private final TemplateEnginePort templateEnginePort;

    public void execute(NotificationLog notification) {
        log.info("Iniciando orquestração de envio. SendId: {}, Canal: {}",
                notification.sendId(), notification.channel());

        try {
            String resolvedBody = resolveBody(notification);

            var logEntry = notification.toBuilder()
                    .content(notification.content().withBody(resolvedBody))
                    .status(NotificationStatusEnun.PENDING)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();

            var savedLog = saveNotificationLogPort.save(logEntry);
            log.info("Notificação registrada no banco com ID: {}", savedLog.id());

            sendMessageBrokerPort.send(savedLog);
            log.info("Notificação encaminhada para a fila de processamento: {}", notification.channel());

        } catch (Exception e) {
            log.error("Erro crítico ao processar notificação SendId: {}. Erro: {}",
                    notification.sendId(), e.getMessage());
            throw e;
        }
    }

    /**
     * Resolve o conteúdo final da mensagem.
     * Se houver template, a lógica de merge entrará aqui.
     */
    private String resolveBody(NotificationLog notification) {

        if (!isNull(notification.template()) && !isNull(notification.template().templateId())) {

            log.debug("Usando template: {}", notification.template().templateId());
            return templateEnginePort.process(
                    notification.template().templateId(),
                    notification.template().params()
            );
        }

        log.debug("Usando corpo direto enviado no request.");
        return notification.content().body();
    }
}