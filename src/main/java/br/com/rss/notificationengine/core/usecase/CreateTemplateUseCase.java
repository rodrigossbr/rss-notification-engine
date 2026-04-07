package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.ports.in.CreateTemplateInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateTemplateUseCase implements CreateTemplateInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    @Override
    public Template execute(Template template) {
        log.info("Criando novo template com a chave: {}", template.getTemplateKey());
        return templatePersistenceOutputPort.save(template);
    }
}