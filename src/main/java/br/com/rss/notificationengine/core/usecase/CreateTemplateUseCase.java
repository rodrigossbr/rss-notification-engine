package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.ports.in.CreateTemplateInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;

public class CreateTemplateUseCase implements CreateTemplateInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    public CreateTemplateUseCase(TemplatePersistenceOutputPort saveTemplatePort) {
        this.templatePersistenceOutputPort = saveTemplatePort;
    }

    @Override
    public Template execute(Template template) {

        return templatePersistenceOutputPort.save(template);
    }
}