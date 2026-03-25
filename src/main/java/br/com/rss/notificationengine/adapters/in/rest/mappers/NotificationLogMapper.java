package br.com.rss.notificationengine.adapters.in.rest.mappers;

import br.com.rss.notificationengine.adapters.in.rest.request.NotificationRequest;
import br.com.rss.notificationengine.core.domain.NotificationContent;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.domain.NotificationTemplate;
import br.com.rss.notificationengine.core.mappers.GenericMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class NotificationLogMapper implements GenericMapper<NotificationRequest, NotificationLog> {

    @Override
    public NotificationLog map(NotificationRequest request) {

        if (isNull(request))
            return null;

        return NotificationLog.builder()
                .sendId(request.sendId())
                .destination(request.destination())
                .channel(request.channel())
                .content(NotificationContent.builder()
                        .body(request.content().body())
                        .attachmentsBase64(request.content().attachmentsBase64())
                        .build())
                .template(request.template() != null ?
                        NotificationTemplate.builder()
                                .templateId(request.template().templateId())
                                .params(request.template().params())
                                .build() : null)
                .build();
    }
}
