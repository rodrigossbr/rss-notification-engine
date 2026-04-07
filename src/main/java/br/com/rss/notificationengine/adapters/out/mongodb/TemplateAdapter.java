package br.com.rss.notificationengine.adapters.out.mongodb;

import br.com.rss.notificationengine.adapters.out.mongodb.mapper.TemplateEntityMapper;
import br.com.rss.notificationengine.adapters.out.mongodb.repository.TemplateRepository;
import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.exception.TemplateAlreadyExistsException;
import br.com.rss.notificationengine.core.ports.out.TemplatePersistenceOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TemplateAdapter implements TemplatePersistenceOutputPort {

    private final TemplateRepository mongoRepository;
    private final TemplateEntityMapper templateEntityMapper;

    @Override
    public Template save(Template domain) {
        try {
            var entity = templateEntityMapper.map(domain);
            var savedEntity = mongoRepository.save(entity);
            return templateEntityMapper.mapBack(savedEntity);
        } catch (DuplicateKeyException e) {
            throw new TemplateAlreadyExistsException(domain.getTemplateKey());
        }
    }

    @Override
    public Optional<Template> findByTemplateKey(String templateKey) {
        return mongoRepository.findByTemplateKey(templateKey)
                .map(templateEntityMapper::mapBack);
    }

    @Override
    public Optional<Template> findById(UUID id) {
        return mongoRepository.findById(id)
                .map(templateEntityMapper::mapBack);
    }

    @Override
    public List<Template> findAll() {
        return templateEntityMapper.toListBack(mongoRepository.findAll());
    }
}
