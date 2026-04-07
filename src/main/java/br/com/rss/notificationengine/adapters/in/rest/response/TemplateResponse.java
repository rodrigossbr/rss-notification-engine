package br.com.rss.notificationengine.adapters.in.rest.response;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Schema(name = "TemplateResponse", description = "Resposta com os dados de um template de notificação")
@Builder
public record TemplateResponse(
        @Schema(
                description = "Identificador interno do template",
                example = "65f1b2a8c9d4e1001a2b3c4d"
        )
        UUID id,

        @Schema(
                description = "Chave única do template",
                example = "welcome-email"
        )
        String templateKey,

        @Schema(
                description = "Nome amigável do template",
                example = "Template de boas-vindas"
        )
        String name,

        @Schema(
                description = "Conteúdo do template",
                example = "Olá {{name}}, seja bem-vindo ao nosso serviço!"
        )
        String content,

        @Schema(
                description = "Canal associado ao template",
                example = "EMAIL",
                allowableValues = {"EMAIL", "SMS", "WHATSAPP", "PUSH"}
        )
        NotificationEnum channel,

        @Schema(
                description = "Data e hora de criação do template",
                example = "2026-03-24T10:15:30Z"
        )
        Instant createdAt,

        @Schema(
                description = "Data e hora da última atualização do template",
                example = "2026-03-24T10:16:10Z"
        )
        Instant updatedAt
) {
}
