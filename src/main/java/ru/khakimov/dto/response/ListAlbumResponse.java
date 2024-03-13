package ru.khakimov.dto.response;

import java.util.List;

public record ListAlbumResponse(
        List<AlbumResponse> albums
) {
}
