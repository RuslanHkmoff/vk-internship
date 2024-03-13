package ru.khakimov.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlbumResponse(
        @JsonProperty("userId") Long userId,
        @JsonProperty("title") String title,
        @JsonProperty("id") Long id
) {
}
