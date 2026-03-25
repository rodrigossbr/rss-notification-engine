package br.com.rss.notificationengine.adapters.out.mongodb;

import br.com.rss.notificationengine.adapters.out.mongodb.mapper.TemplateEntityMapper;
import br.com.rss.notificationengine.adapters.out.mongodb.repository.TemplateRepository;
import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.ports.in.TemplatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TemplateAdapter implements TemplatePort {

    private final TemplateRepository mongoRepository;
    private final TemplateEntityMapper templateEntityMapper;

    @Override
    public Template save(Template domain) {
        var entity = templateEntityMapper.map(domain);

        var savedEntity = mongoRepository.save(entity);

        return templateEntityMapper.mapBack(savedEntity);
    }

    @Override
    public Optional<Template> findByTemplateKey(String templateKey) {
        return mongoRepository.findByTemplateKey(templateKey)
                .map(templateEntityMapper::mapBack);
    }

    @Override
    public Optional<Template> findById(String id) {
        return mongoRepository.findById(id)
                .map(templateEntityMapper::mapBack);
    }

    @Override
    public List<Template> findAll() {
        return templateEntityMapper.toListBack(mongoRepository.findAll());
    }
}
