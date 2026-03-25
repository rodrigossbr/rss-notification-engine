package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnun;
import br.com.rss.notificationengine.core.exception.BusinessException;
import br.com.rss.notificationengine.core.ports.in.SendNotificationInputPort;
import br.com.rss.notificationengine.core.ports.out.NotificationLogOutputPort;
import br.com.rss.notificationengine.core.ports.out.SendMessageBrokerOutputPort;
import br.com.rss.notificationengine.core.ports.out.TemplateEngineOutputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.isNull;


@Slf4j
public class SendNotificationUseCase implements SendNotificationInputPort {

    private final NotificationLogOutputPort notificationLogOutputPort;
    private final SendMessageBrokerOutputPort sendMessageBrokerOutputPort;
    private final TemplateEngineOutputPort templateEngineOutputPort;
    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    public SendNotificationUseCase(NotificationLogOutputPort saveNotificationLogPort,
                                   SendMessageBrokerOutputPort sendMessageBrokerPort,
                                   TemplateEngineOutputPort templateEnginePort,
                                   TemplatePersistenceOutputPort templatePersistencePort) {
        this.notificationLogOutputPort = saveNotificationLogPort;
        this.sendMessageBrokerOutputPort = sendMessageBrokerPort;
        this.templateEngineOutputPort = templateEnginePort;
        this.templatePersistenceOutputPort = templatePersistencePort;
    }

    @Override
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

            var savedLog = notificationLogOutputPort.save(logEntry);
            log.info("Notificação registrada no banco com ID: {}", savedLog.id());

            sendMessageBrokerOutputPort.send(savedLog);
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

            var template = templatePersistenceOutputPort.findById(UUID.fromString(notification.template().templateId()))
                    .orElseThrow(() -> new BusinessException("Template não encontrado: " + notification.template().templateId()));

            return templateEngineOutputPort.process(
                    template.getContent(),
                    notification.template().params()
            );
        }

        log.debug("Usando corpo direto enviado no request.");
        return notification.content().body();
    }
}