package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.exception.TemplateNotFoundException;
import br.com.rss.notificationengine.core.ports.in.UpdateTemplateInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UpdateTemplateUseCase implements UpdateTemplateInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    @Override
    public Template execute(UUID id, Template updatedData) {
        log.info("Iniciando atualização do template ID: {}", id);

        Template template = templatePersistenceOutputPort.findById(id)
                .orElseThrow(() -> {
                    log.warn("Template não encontrado para atualização. ID: {}", id);
                    return new TemplateNotFoundException(id.toString());
                });

        template.update(updatedData);

        var savedTemplate = templatePersistenceOutputPort.save(template);
        log.info("Template ID: {} atualizado com sucesso", id);

        return savedTemplate;
    }
}
