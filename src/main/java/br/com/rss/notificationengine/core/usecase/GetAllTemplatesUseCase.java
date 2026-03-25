package br.com.rss.notificationengine.core.usecase;

import br.com.rss.notificationengine.core.domain.Template;
import br.com.rss.notificationengine.core.ports.in.TemplatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTemplatesUseCase {

    private final TemplatePort repository;

    public List<Template> execute() {
        return repository.findAll();
    }
}
