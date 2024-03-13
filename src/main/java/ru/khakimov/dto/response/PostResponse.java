package ru.khakimov.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("userId") Long userId,
        @JsonProperty("title") String title,
        @JsonProperty("body") String body
) {
}
