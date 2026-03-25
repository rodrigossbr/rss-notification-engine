package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.exception.TemplateNotFoundException;
import br.com.rss.notificationengine.core.ports.in.GetTemplateInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;

public class GetTemplateUseCase implements GetTemplateInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    public GetTemplateUseCase(TemplatePersistenceOutputPort templatePort) {
        this.templatePersistenceOutputPort = templatePort;
    }

    @Override
    public Template execute(String key) {

        return templatePersistenceOutputPort.findByTemplateKey(key)
                .orElseThrow(() -> new TemplateNotFoundException(key));
    }
}
