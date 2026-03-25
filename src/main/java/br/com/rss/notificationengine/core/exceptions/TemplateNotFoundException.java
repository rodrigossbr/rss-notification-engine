package br.com.rss.notificationengine.core.exceptions;

public class TemplateNotFoundException extends RuntimeException {
    public TemplateNotFoundException(String key) {
        super("Template não encontrado com a chave: " + key);
    }
}
