package br.com.rss.notificationengine.core.exception;

public class TemplateAlreadyExistsException extends BusinessException {
    public TemplateAlreadyExistsException(String key) {
        super("Já existe um template cadastrado com a chave: " + key);
    }
}
