package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.ports.in.GetAllTemplatesInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;

import java.util.List;

public class GetAllTemplatesUseCase implements GetAllTemplatesInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    public GetAllTemplatesUseCase(TemplatePersistenceOutputPort repository) {
        this.templatePersistenceOutputPort = repository;
    }

    @Override
    public List<Template> execute() {
        return templatePersistenceOutputPort.findAll();
    }
}
