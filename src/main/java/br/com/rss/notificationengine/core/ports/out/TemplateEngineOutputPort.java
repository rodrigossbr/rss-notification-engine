package br.com.rss.notificationengine.core.ports.out;

import java.util.Map;

public interface TemplateEngineOutputPort {

    /**
     * Resolve o template substituindo os parâmetros.
     */
    String process(String templateContent, Map<String, String> params);
}
