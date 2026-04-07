package br.com.rss.notificationengine.adapters.in.rest.request;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(
        name = "TemplateRequest",
        description = "Payload para criação de template de notificação"
)
public record TemplateRequest(
        @Schema(
                description = "Chave única do template",
                example = "welcome-email"
        )
        @NotBlank(message = "A chave do template é obrigatória")
        String templateKey,

        @Schema(
                description = "Nome amigável do template",
                example = "Template de boas-vindas"
        )
        @NotBlank(message = "O nome do template é obrigatório")
        String name,

        @Schema(
                description = "Conteúdo do template com placeholders para substituição",
                example = "Olá {{name}}, seja bem-vindo ao nosso serviço!"
        )
        @NotBlank(message = "O conteúdo do template não pode estar vazio")
        String content,

        @Schema(
                description = "Canal de envio associado ao template",
                example = "EMAIL",
                allowableValues = {"EMAIL", "SMS", "WHATSAPP", "PUSH"}
        )
        @NotNull(message = "O canal de envio deve ser informado")
        NotificationEnum channel
) {
}
