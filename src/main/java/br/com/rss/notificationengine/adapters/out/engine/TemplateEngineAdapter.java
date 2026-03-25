package br.com.rss.notificationengine.adapters.out.engine;

import br.com.rss.notificationengine.core.ports.out.TemplateEngineOutputPort;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TemplateEngineAdapter implements TemplateEngineOutputPort {

    @Override
    public String process(String templateContent, Map<String, String> params) {
        String content = templateContent;

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
        }
        return content;
    }
}
