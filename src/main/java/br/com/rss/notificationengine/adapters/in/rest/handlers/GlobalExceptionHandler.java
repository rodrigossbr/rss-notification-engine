package br.com.rss.notificationengine.adapters.in.rest.handlers;

import br.com.rss.notificationengine.core.exception.BusinessException;
import br.com.rss.notificationengine.core.exception.TemplateAlreadyExistsException;
import br.com.rss.notificationengine.core.exception.TemplateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {

        HttpStatus status = switch (ex) {
            case TemplateNotFoundException _ -> HttpStatus.NOT_FOUND;
            case TemplateAlreadyExistsException _ -> HttpStatus.CONFLICT;
            default -> HttpStatus.BAD_REQUEST;
        };

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                status,
                ex.getMessage()
        );

        problemDetail.setTitle("Erro de Negócio");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {

        String detail = ex.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                detail
        );

        problemDetail.setTitle("Erro de Validação");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}
