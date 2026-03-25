package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.Template;

import java.util.List;

public interface GetAllTemplatesInputPort {
    List<Template> execute();
}
