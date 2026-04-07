package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.exception.TemplateNotFoundException;
import br.com.rss.notificationengine.core.ports.in.GetTemplateInputPort;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetTemplateUseCase implements GetTemplateInputPort {

    private final TemplatePersistenceOutputPort templatePersistenceOutputPort;

    @Override
    public Template execute(String key) {
        log.info("Buscando template pela chave: {}", key);
        return templatePersistenceOutputPort.findByTemplateKey(key)
                .orElseThrow(() -> {
                    log.warn("Template não encontrado para a chave: {}", key);
                    return new TemplateNotFoundException(key);
                });
    }
}
