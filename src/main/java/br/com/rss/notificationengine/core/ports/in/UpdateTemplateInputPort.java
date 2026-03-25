package br.com.rss.notificationengine.core.ports.in;

import br.com.rss.notificationengine.core.domain.Template;

import java.util.UUID;

public interface UpdateTemplateInputPort {
    Template execute(UUID id, Template updatedData);
}
