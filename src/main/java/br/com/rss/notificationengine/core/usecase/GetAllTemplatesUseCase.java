package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.ports.in.GetAllTemplatesInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class GetAllTemplatesUseCase implements GetAllTemplatesInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    @Override
    public List<Template> execute() {
        log.info("Buscando todos os templates");
        return templatePersistenceOutputPort.findAll();
    }
}
