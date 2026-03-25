package br.com.rss.notificationengine.core.domain;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnun;
import lombok.*;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {
    private String id;

    @Setter
    private String templateKey;

    @Setter
    private String name;

    @Setter
    private String content;

    @Setter
    private NotificationEnun channel;

    private Instant createdAt;

    @Setter
    private Instant updatedAt;

    public void update(Template newData) {
        this.templateKey = newData.getTemplateKey().toUpperCase();
        this.name = newData.getName();
        this.content = newData.getContent();
        this.channel = newData.getChannel();
        this.updatedAt = Instant.now();
    }
}
