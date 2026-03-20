package br.com.rss.notificationengine.adapters.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record NotificationRequest(
        @NotBlank String userId,
        @NotBlank String destination,
        @NotBlank String channel, // EMAIL, SMS, etc.
        @NotNull Map<String, Object> payload
) {}