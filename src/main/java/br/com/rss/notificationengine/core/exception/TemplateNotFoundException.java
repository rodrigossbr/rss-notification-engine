package br.com.rss.notificationengine.core.exception;

public class TemplateNotFoundException extends BusinessException {
    public TemplateNotFoundException(String key) {
        super("Template não encontrado com a chave: " + key);
    }
}
