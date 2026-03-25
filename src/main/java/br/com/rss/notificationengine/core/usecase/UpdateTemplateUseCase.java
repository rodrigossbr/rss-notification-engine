package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.exception.TemplateNotFoundException;
import br.com.rss.notificationengine.core.ports.in.UpdateTemplateInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;

import java.util.UUID;

public class UpdateTemplateUseCase implements UpdateTemplateInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    public UpdateTemplateUseCase(TemplatePersistenceOutputPort templatePort) {
        this.templatePersistenceOutputPort = templatePort;
    }

    @Override
    public Template execute(UUID id, Template updatedData) {

        Template template = templatePersistenceOutputPort.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id.toString()));

        template.update(updatedData);

        return templatePersistenceOutputPort.save(template);
    }
}
