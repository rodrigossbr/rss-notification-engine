package br.com.rss.notificationengine.adapters.in.rest.controllers;

import br.com.rss.notificationengine.adapters.in.rest.mappers.TemplateMapper;
import br.com.rss.notificationengine.adapters.in.rest.mappers.TemplateResponseMapper;
import br.com.rss.notificationengine.adapters.in.rest.request.TemplateRequest;
import br.com.rss.notificationengine.adapters.in.rest.response.StandardResponse;
import br.com.rss.notificationengine.adapters.in.rest.response.TemplateResponse;
import br.com.rss.notificationengine.core.ports.in.CreateTemplateInputPort;
import br.com.rss.notificationengine.core.ports.in.GetAllTemplatesInputPort;
import br.com.rss.notificationengine.core.ports.in.GetTemplateInputPort;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/templates")
@RequiredArgsConstructor
@Tag(name = "Templates", description = "Endpoints para criação e consulta de templates")
public class TemplateController {

    private final CreateTemplateInputPort createTemplateUseCase;
    private final GetTemplateInputPort getTemplateUseCase;
    private final GetAllTemplatesInputPort getAllTemplatesUseCase;

    private final TemplateMapper templateMapper;
    private final TemplateResponseMapper templateResponseMapper;

    @Operation(
            summary = "Listar templates",
            description = "Retorna todos os templates cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de templates recuperada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno ao recuperar os templates"
            )
    })
    @GetMapping
    public ResponseEntity<StandardResponse<List<TemplateResponse>>> getAll() {
        var domains = getAllTemplatesUseCase.execute();

        var responseList = templateResponseMapper.toList(domains);

        return ResponseEntity.ok(
                StandardResponse.success("Lista de templates recuperada com sucesso!", responseList)
        );
    }

    @Operation(
            summary = "Buscar template por chave",
            description = "Retorna um template específico a partir da sua chave."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Template encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Template não encontrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno ao buscar o template"
            )
    })
    @GetMapping("/{templateKey}")
    public ResponseEntity<StandardResponse<TemplateResponse>> get(
            @Parameter(
                    description = "Chave única do template",
                    example = "welcome-email",
                    required = true
            )
            @PathVariable String templateKey
    ) {

        var domain = getTemplateUseCase.execute(templateKey);

        return ResponseEntity.ok(StandardResponse
                .success("Template encontrado com sucesso!", templateResponseMapper.map(domain))
        );
    }

    @Operation(
            summary = "Criar template",
            description = "Cria um novo template de notificação."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Template criado com sucesso",
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
                    description = "Erro interno ao criar o template",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping
    public ResponseEntity<StandardResponse<TemplateResponse>> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados do template a ser criado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de template",
                                    value = """
                                            {
                                              "templateKey": "welcome-email",
                                              "name": "Template de boas-vindas",
                                              "content": "Olá {{name}}, seja bem-vindo ao nosso serviço!",
                                              "channel": "EMAIL"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody @Valid TemplateRequest request
    ) {

        var templateDomain = templateMapper.map(request);
        var saved = createTemplateUseCase.execute(templateDomain);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StandardResponse
                        .success("Template criado com sucesso!", templateResponseMapper.map(saved))
                );
    }
}
