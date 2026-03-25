package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.exceptions.TemplateNotFoundException;
import br.com.rss.notificationengine.core.ports.in.TemplatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTemplateUseCase {

    private final TemplatePort templatePort;

    public Template execute(String key) {

        return templatePort.findByTemplateKey(key)
                .orElseThrow(() -> new TemplateNotFoundException(key));
    }
}
