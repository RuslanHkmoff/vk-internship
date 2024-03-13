package ru.khakimov.dto.response;

import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

public record ApiErrorResponse(
        @NotNull
        String description,
        @NotNull
        String code,
        @NotNull
        String exceptionName,
        @NotNull
        String exceptionMessage,
        List<String> stacktrace) {
    public static ApiErrorResponse buildResponse(String description, String code, Exception e) {
        return new ApiErrorResponse(
                description,
                code,
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toList()
        );
    }
}