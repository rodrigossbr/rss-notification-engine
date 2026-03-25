package br.com.rss.notificationengine.core.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record NotificationContent(
        @NotBlank String body,
        List<String> attachmentsBase64
) {
    public NotificationContent withBody(String newBody) {
        return new NotificationContent(newBody, this.attachmentsBase64);
    }
}