package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.Template;

public interface GetTemplateInputPort {
    Template execute(String key);
}
