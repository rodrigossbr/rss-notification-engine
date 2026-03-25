package br.com.rss.notificationengine.core.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Map;

@Builder
public record NotificationTemplate(
        @NotBlank String templateId,
        Map<String, String> params
) {}
