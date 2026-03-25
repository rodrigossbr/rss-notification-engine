package br.com.rss.notificationengine.adapters.in.rest.mappers;

import br.com.rss.notificationengine.adapters.in.rest.response.TemplateResponse;
import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.mapper.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class TemplateResponseMapper implements GenericMapper<Template, TemplateResponse> {

    @Override
    public TemplateResponse map(Template template) {

        if (isNull(template))
            return null;

        return TemplateResponse.builder()
                .id(template.getId())
                .templateKey(template.getTemplateKey().toUpperCase())
                .name(template.getName())
                .content(template.getContent())
                .channel(template.getChannel())
                .updatedAt(template.getUpdatedAt())
                .createdAt(template.getCreatedAt())
                .build();
    }
}
