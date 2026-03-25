package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.Template;

public interface CreateTemplateInputPort {
    Template execute(Template template);
}
