package br.com.rss.notificationengine.core.domain;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnum;
import lombok.*;

import java.util.UUID;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {
    private UUID id;
    private String templateKey;
    private String name;
    private String content;
    private NotificationEnum channel;
    private Instant createdAt;
    private Instant updatedAt;

    public void update(Template newData) {
        this.templateKey = newData.getTemplateKey() != null ? newData.getTemplateKey().toUpperCase() : this.templateKey;
        this.name = newData.getName() != null ? newData.getName() : this.name;
        this.content = newData.getContent() != null ? newData.getContent() : this.content;
        this.channel = newData.getChannel() != null ? newData.getChannel() : this.channel;
    }
}
