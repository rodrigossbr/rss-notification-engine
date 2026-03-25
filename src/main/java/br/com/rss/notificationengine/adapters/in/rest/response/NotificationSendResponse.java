package br.com.rss.notificationengine.adapters.in.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Schema(name = "NotificationSendResponse", description = "Resposta retornada após aceitar uma notificação para envio")
@Builder
public record NotificationSendResponse(
        @Schema(
                description = "Identificador do envio da notificação",
                example = "f3a2c1b0-7d2e-4f3a-9d11-1a2b3c4d5e6f"
        )
        UUID sendId,

        @Schema(
                description = "Mensagem de retorno do processamento",
                example = "Enviado para a fila"
        )
        String message,

        @Schema(
                description = "Data e hora em que a resposta foi gerada",
                example = "2026-03-24T10:15:30Z"
        )
        Instant timestamp
) {
}