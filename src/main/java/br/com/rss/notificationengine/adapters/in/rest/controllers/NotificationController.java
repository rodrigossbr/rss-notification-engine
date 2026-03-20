package br.com.rss.notificationengine.adapters.in.rest.controllers;

import br.com.rss.notificationengine.adapters.in.rest.dto.NotificationRequest;
import br.com.rss.notificationengine.core.domain.NotificationLog;
import br.com.rss.notificationengine.core.usecase.SendNotificationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notifications")
@Tag(name = "Notifications", description = "Endpoints para envio e gerenciamento de notificações")
public class NotificationController {

    private final SendNotificationUseCase useCase;

    public NotificationController(SendNotificationUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(summary = "Enviar nova notificação", description = "Recebe um payload e roteia para o canal correto (Email, SMS, etc)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Notificação aceita e enviada para a fila"),
            @ApiResponse(responseCode = "400", description = "Payload inválido")
    })
    @PostMapping
    public ResponseEntity<String> send(@RequestBody @Valid NotificationRequest request) {
        // Converte o DTO para o Domain Object
        var notification = new NotificationLog(
                null, // O MongoDB gera o ID
                request.userId(),
                request.destination(),
                request.channel(),
                "PENDING",
                request.payload(),
                null // O @CreatedDate preenche
        );

        useCase.execute(notification);

        return ResponseEntity.accepted().body("Notificação enviada para processamento!");
    }
}