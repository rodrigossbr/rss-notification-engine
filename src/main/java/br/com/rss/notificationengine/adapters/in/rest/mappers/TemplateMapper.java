package br.com.rss.notificationengine.adapters.in.rest.mappers;

import br.com.rss.notificationengine.adapters.in.rest.request.TemplateRequest;
import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.mappers.GenericMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TemplateMapper implements GenericMapper<TemplateRequest, Template> {

    @Override
    public Template map(TemplateRequest request) {

        if (isNull(request))
            return null;

        return Template.builder()
                .templateKey(request.templateKey().toUpperCase())
                .name(request.name())
                .content(request.content())
                .channel(request.channel())
                .build();
    }
}
