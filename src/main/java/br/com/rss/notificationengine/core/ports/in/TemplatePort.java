package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.Template;

import java.util.List;
import java.util.Optional;

public interface TemplatePort {

    Template save(Template template);

    Optional<Template> findByTemplateKey(String templateKey);

    Optional<Template> findById(String id);

    List<Template> findAll();
}
