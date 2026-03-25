package br.com.rss.notificationengine.core.ports.out;

import java.util.Map;

public interface TemplateEnginePort {

    /**
     * Resolve o template buscando o conteúdo e substituindo os parâmetros.
     */
    String process(String templateId, Map<String, String> params);
}
