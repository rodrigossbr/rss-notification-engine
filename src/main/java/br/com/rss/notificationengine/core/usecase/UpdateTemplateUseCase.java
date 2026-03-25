package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.exceptions.TemplateNotFoundException;
import br.com.rss.notificationengine.core.ports.in.TemplatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTemplateUseCase {

    private final TemplatePort templatePort;

    public Template execute(String id, Template updatedData) {

        Template template = templatePort.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));

        template.update(updatedData);

        return templatePort.save(template);
    }
}
