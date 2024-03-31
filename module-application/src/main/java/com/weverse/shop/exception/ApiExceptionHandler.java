package com.weverse.shop.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorResponse {
        private int code;
        private String message;
        private String customMessage;
        private List<BindingResultError> bindingErrorMessage;
        private String path;
        private String stackTrace;

        @JsonIgnore
        private HttpStatus httpStatus;
    }

    @Builder
    @Getter
    public static class BindingResultError{
        private String field;
        private String message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .bindingErrorMessage(getBindingResultErrors(exception.getBindingResult()))
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest request) {
        return ResponseEntity
                .status(MissingRequestValueException.class.isAssignableFrom(exception.getClass()) ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .path(request.getDescription(false))
                        .message(exception.getMessage())
                        .stackTrace(ExceptionUtils.getStackTrace(exception))
                        .build());
    }

    private List<BindingResultError> getBindingResultErrors(BindingResult bindingResult){
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> BindingResultError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
