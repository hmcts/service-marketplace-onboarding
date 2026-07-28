package uk.gov.hmcts.cp.onboarding.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.encoder.Encode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.hmcts.cp.onboarding.services.ClockService;
import uk.gov.hmcts.cp.openapi.model.ErrorResponse;

import java.util.Objects;

import io.micrometer.tracing.Tracer;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final Tracer tracer;
    private final ClockService clockService;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(final ResponseStatusException ex) {
        if (ex.getStatusCode().is4xxClientError()) {
            log.warn("Client error: {}", Encode.forJava(ex.getReason()));
        } else {
            log.error("Server error: {}", Encode.forJava(ex.getReason()));
        }
        return ResponseEntity.status(ex.getStatusCode()).body(buildErrorResponse(ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception ex) {
        log.error("Unexpected error", ex);
        return ResponseEntity.internalServerError().body(buildErrorResponse("An unexpected error occurred"));
    }

    private ErrorResponse buildErrorResponse(final String message) {
        return ErrorResponse.builder()
                .message(message)
                .timestamp(clockService.now())
                .traceId(Objects.requireNonNull(tracer.currentSpan()).context().traceId())
                .build();
    }
}
