package ru.khakimov.dto.request;

import jakarta.validation.constraints.Positive;

public record PostRequest(
        @Positive
        Long userId,
        String title,
        String body
) {
}
