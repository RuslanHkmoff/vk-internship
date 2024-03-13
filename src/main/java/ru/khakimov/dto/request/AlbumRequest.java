package ru.khakimov.dto.request;

import jakarta.validation.constraints.Positive;

public record AlbumRequest(
        @Positive
        Long userId,
        String title
) {
}
