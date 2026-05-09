package org.treasure.accountservice.web.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.treasure.accountservice.exception.AbstractException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorResponse> handle(final HttpServletRequest request,
                                                final Throwable ex) {
        final var httpStatus = getStatus(ex);
        final var exception = (AbstractException) ex;

        final var errorResponse = buildErrorResponse(httpStatus, request, Optional.of(exception));

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(final HttpServletRequest request,
                                                final MethodArgumentNotValidException ex) {
        final var errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(fieldError -> ErrorResponse.Detail.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build()
            )
            .toList();

        final var errorResponse = buildBadErrorResponse(request, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private HttpStatus getStatus(final Throwable ex) {
        if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
            return ex.getClass().getAnnotation(ResponseStatus.class).value();
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private ErrorResponse buildErrorResponse(final HttpStatus httpStatus,
                                             final HttpServletRequest request,
                                             final Optional<AbstractException> exception) {
        return new ErrorResponse(
            httpStatus.name(),
            exception.map(AbstractException::getMessage).orElse("An unexpected error occurred"),
            Instant.now(),
            null
        );
    }

    private ErrorResponse buildBadErrorResponse(final HttpServletRequest request,
                                                final List<ErrorResponse.Detail> errors) {
        return ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.name())
            .message("Validation failed for the request")
            .timestamp(Instant.now())
            .errors(errors)
            .build();
    }
}