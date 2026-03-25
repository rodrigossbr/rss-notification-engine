package br.com.rss.notificationengine.adapters.in.rest.request;

import br.com.rss.notificationengine.core.domain.enums.NotificationEnun;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

@Schema(
        name = "NotificationRequest",
        description = "Payload de envio de notificação"
)
public record NotificationRequest(
        @Schema(
                description = "Identificador do envio da notificação",
                example = "f3a2c1b0-7d2e-4f3a-9d11-1a2b3c4d5e6f"
        )
        @NotBlank(message = "O sendId é obrigatório")
        String sendId,

        @Schema(
                description = "Destino da notificação. Pode ser e-mail, telefone, token de push, etc.",
                example = "usuario@dominio.com"
        )
        @NotBlank(message = "O destino é obrigatório")
        String destination,

        @Schema(
                description = "Canal da notificação",
                example = "EMAIL",
                allowableValues = {"EMAIL", "SMS", "WHATSAPP", "PUSH"}
        )
        @NotNull(message = "O canal (EMAIL, SMS, etc) é obrigatório")
        NotificationEnun channel,

        @Valid
        @NotNull(message = "O conteúdo da notificação é obrigatório")
        @Schema(
                description = "Conteúdo principal da notificação"
        )
        NotificationContentRequest content,

        @Valid
        @Schema(
                description = "Template opcional utilizado para compor a notificação"
        )
        NotificationTemplateRequest template
) {

    @Schema(name = "NotificationContentRequest", description = "Conteúdo textual e anexos da notificação")
    public record NotificationContentRequest(
            @Schema(
                    description = "Corpo da mensagem da notificação",
                    example = "Sua notificação foi enviada com sucesso"
            )
            @NotBlank(message = "O corpo da mensagem é obrigatório")
            String body,

            @ArraySchema(
                    schema = @Schema(
                            description = "Anexos codificados em Base64",
                            example = "iVBORw0KGgoAAAANSUhEUgAA..."
                    )
            )
            List<String> attachmentsBase64
    ) {
    }

    @Schema(name = "NotificationTemplateRequest", description = "Dados opcionais para uso de template")
    public record NotificationTemplateRequest(
            @Schema(
                    description = "Identificador do template",
                    example = "welcome-email"
            )
            @NotBlank(message = "O ID do template é obrigatório")
            String templateId,

            @Schema(
                    description = "Parâmetros dinâmicos para o template",
                    example = "{\"name\":\"Rodrigo\",\"plan\":\"Premium\"}"
            )
            Map<String, String> params
    ) {
    }
}