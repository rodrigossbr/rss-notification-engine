package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.ports.in.TemplatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTemplateUseCase {

    private final TemplatePort saveTemplatePort;

    public Template execute(Template template) {

        return saveTemplatePort.save(template);
    }
}