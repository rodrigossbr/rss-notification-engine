package br.com.rss.notificationengine.adapters.in.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(
        name = "StandardResponse",
        description = "Envelope padrão de resposta da API"
)
public record StandardResponse<T>(
        @Schema(
                description = "Mensagem resumida do resultado da operação",
                example = "Operação realizada com sucesso"
        )
        String message,

        @Schema(
                description = "Dados retornados pela operação"
        )
        T data,

        @Schema(
                description = "Momento em que a resposta foi gerada",
                example = "2026-03-24T10:15:30Z"
        )
        Instant timestamp
) {
    public static <T> StandardResponse<T> success(String message, T data) {
        return new StandardResponse<>(message, data, Instant.now());
    }
}
