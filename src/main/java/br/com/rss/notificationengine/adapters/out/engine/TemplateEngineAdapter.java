package br.com.rss.notificationengine.adapters.out.engine;

import br.com.rss.notificationengine.adapters.out.mongodb.repository.TemplateRepository;
import br.com.rss.notificationengine.core.ports.out.TemplateEnginePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateEngineAdapter implements TemplateEnginePort {

    private final TemplateRepository repository;

    @Override
    public String process(String templateId, Map<String, String> params) {
        var template = repository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template não encontrado: " + templateId));

        String content = template.getContent();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
        }
        return content;
    }
}
