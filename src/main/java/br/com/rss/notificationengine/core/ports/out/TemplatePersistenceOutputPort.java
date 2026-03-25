package br.com.rss.notificationengine.core.ports.out;

import br.com.rss.notificationengine.core.domain.Template;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TemplatePersistenceOutputPort {

    Template save(Template template);

    Optional<Template> findByTemplateKey(String templateKey);

    Optional<Template> findById(UUID id);

    List<Template> findAll();
}
