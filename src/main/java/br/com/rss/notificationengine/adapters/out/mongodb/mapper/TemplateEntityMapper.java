package br.com.rss.notificationengine.adapters.out.mongodb.mapper;

import br.com.rss.notificationengine.adapters.out.mongodb.entity.TemplateEntity;
import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.mapper.GenericMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class TemplateEntityMapper implements GenericMapper<Template, TemplateEntity> {

    @Override
    public TemplateEntity map(Template input) {

        if (isNull(input)) {
            return null;
        }

        return TemplateEntity.builder()
                .id(isNull(input.getId()) ? UUID.randomUUID() : input.getId())
                .templateKey(input.getTemplateKey())
                .name(input.getName())
                .content(input.getContent())
                .channel(input.getChannel())
                .createdAt(isNull(input.getCreatedAt()) ? Instant.now() : input.getCreatedAt())
                .updatedAt(isNull(input.getUpdatedAt()) ? Instant.now() : input.getUpdatedAt())
                .build();
    }

    @Override
    public Template mapBack(TemplateEntity entity) {

        if (isNull(entity)) {
            return null;
        }

        return Template.builder()
                .id(entity.getId())
                .templateKey(entity.getTemplateKey())
                .name(entity.getName())
                .content(entity.getContent())
                .channel(entity.getChannel())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
