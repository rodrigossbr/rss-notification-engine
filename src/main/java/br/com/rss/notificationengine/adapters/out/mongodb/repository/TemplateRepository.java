package br.com.rss.notificationengine.adapters.out.mongodb.repository;

import br.com.rss.notificationengine.adapters.out.mongodb.entity.TemplateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface TemplateRepository extends MongoRepository<TemplateEntity, UUID> {

    Optional<TemplateEntity> findByTemplateKey(String templateKey);
}