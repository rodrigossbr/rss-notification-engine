package br.com.rss.notificationengine.adapters.in.rest.response;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnum;
import br.com.rss.notificationengine.core.domain.enums.NotificationStatusEnum;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Schema(
        name = "NotificationLogResponse",
        description = "Resposta com os dados do log de notificação"
)
@Builder
public record NotificationLogResponse(
        @Schema(
                description = "Identificador do envio da notificação",
                example = "f3a2c1b0-7d2e-4f3a-9d11-1a2b3c4d5e6f"
        )
        UUID sendId,

        @Schema(
                description = "Destino da notificação",
                example = "usuario@dominio.com"
        )
        String destination,

        @Schema(
                description = "Canal utilizado no envio",
                example = "EMAIL",
                allowableValues = {"EMAIL", "SMS", "WHATSAPP", "PUSH"}
        )
        NotificationEnum channel,

        @Schema(
                description = "Status atual do processamento da notificação",
                example = "SENT",
                allowableValues = {"PENDING", "PROCESSING", "SENT", "FAILED"}
        )
        NotificationStatusEnum status,

        @Schema(
                description = "Conteúdo efetivo da notificação"
        )
        NotificationContentResponse content,

        @Schema(
                description = "Data e hora de criação do log",
                example = "2026-03-24T10:15:30Z"
        )
        Instant createdAt,

        @Schema(
                description = "Data e hora da última atualização do log",
                example = "2026-03-24T10:16:10Z"
        )
        Instant updatedAt
) {

    @Schema(name = "NotificationContentResponse", description = "Conteúdo textual e anexos da notificação")
    @Builder
    public record NotificationContentResponse(
            @Schema(
                    description = "Corpo da mensagem",
                    example = "Sua notificação foi enviada com sucesso"
            )
            String body,

            @ArraySchema(
                    schema = @Schema(
                            description = "Anexos em Base64",
                            example = "iVBORw0KGgoAAAANSUhEUgAA..."
                    )
            )
            List<String> attachmentsBase64
    ) {
    }
}
