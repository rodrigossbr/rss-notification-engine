package br.com.rss.notificationengine.adapters.in.rest.controllers;

import br.com.rss.notificationengine.adapters.in.rest.mappers.NotificationLogMapper;
import br.com.rss.notificationengine.adapters.in.rest.mappers.NotificationLogResponseMapper;
import br.com.rss.notificationengine.adapters.in.rest.request.NotificationRequest;
import br.com.rss.notificationengine.adapters.in.rest.response.NotificationLogResponse;
import br.com.rss.notificationengine.adapters.in.rest.response.NotificationSendResponse;
import br.com.rss.notificationengine.adapters.in.rest.response.StandardResponse;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.ports.in.GetNotificationLogsBySendIdInputPort;
import br.com.rss.notificationengine.core.ports.in.SendNotificationInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "Endpoints para envio e gerenciamento de notificações")
public class NotificationController {

    private final SendNotificationInputPort useCase;
    private final GetNotificationLogsBySendIdInputPort getNotificationLogsBySendIdUseCase;

    private final NotificationLogMapper notificationLogMapper;
    private final NotificationLogResponseMapper notificationLogResponseMapper;

    @Operation(
            summary = "Buscar logs por sendId",
            description = "Retorna os logs de processamento vinculados a um identificador de envio (sendId)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logs recuperados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro sendId inválido"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno ao recuperar os logs"
            )
    })
    @GetMapping("/logs/{sendId}")
    public ResponseEntity<StandardResponse<List<NotificationLogResponse>>> getLogsBySendId(
            @Parameter(
                    description = "Identificador do envio da notificação",
                    example = "f3a2c1b0-7d2e-4f3a-9d11-1a2b3c4d5e6f",
                    required = true
            )
            @PathVariable String sendId
    ) {

        List<NotificationLog> logs = getNotificationLogsBySendIdUseCase.execute(UUID.fromString(sendId));

        List<NotificationLogResponse> responseList = notificationLogResponseMapper.toList(logs);

        return ResponseEntity.ok(
                StandardResponse.success("Logs recuperados com sucesso para o envio: " + sendId, responseList)
        );
    }

    @Operation(
            summary = "Enviar nova notificação",
            description = "Recebe um payload de notificação e encaminha para o fluxo de envio assíncrono."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "Notificação aceita e enviada para a fila",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Payload inválido",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno ao processar a notificação",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping
    public ResponseEntity<StandardResponse<NotificationSendResponse>> send(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados da notificação a ser enviada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de notificação",
                                    value = """
                                            {
                                              "channel": "EMAIL",
                                              "recipient": "usuario@dominio.com",
                                              "subject": "Bem-vindo",
                                              "message": "Sua notificação foi enviada com sucesso"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody @Valid NotificationRequest request
    ) {

        var notification = notificationLogMapper.map(request);

        useCase.execute(notification);

        var data = NotificationSendResponse
                .builder()
                .sendId(notification.sendId())
                .message("Enviado para a fila")
                .timestamp(Instant.now())
                .build();

        return ResponseEntity
                .accepted()
                .body(StandardResponse
                        .success("Notificação aceita com sucesso!", data)
                );
    }
}