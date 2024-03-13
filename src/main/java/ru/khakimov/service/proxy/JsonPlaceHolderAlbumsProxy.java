package ru.khakimov.service.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.khakimov.dto.request.AlbumRequest;
import ru.khakimov.dto.response.AlbumResponse;
import ru.khakimov.dto.response.ListAlbumResponse;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JsonPlaceHolderAlbumsProxy {
    private static final String ALBUM_URL = "/albums";
    private static final long CACHE_DURATION = 10;
    private final WebClient jsonPlaceHolderClient;

    public Mono<AlbumResponse> getAlbumById(Long id) {
        return jsonPlaceHolderClient
                .get()
                .uri(String.join("/", ALBUM_URL, id.toString()))
                .retrieve()
                .bodyToMono(AlbumResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }

    public Mono<ListAlbumResponse> getAll() {
        return jsonPlaceHolderClient
                .get()
                .uri(ALBUM_URL)
                .retrieve()
                .bodyToFlux(AlbumResponse.class)
                .collectList()
                .map(ListAlbumResponse::new)
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }

    public Mono<AlbumResponse> createAlbum(AlbumRequest albumRequest) {
        return jsonPlaceHolderClient
                .post()
                .uri(ALBUM_URL)
                .bodyValue(albumRequest)
                .retrieve()
                .bodyToMono(AlbumResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }

    public Mono<AlbumResponse> updateAlbum(Long id, AlbumRequest albumRequest) {
        return jsonPlaceHolderClient
                .put()
                .uri(String.join("/", ALBUM_URL, id.toString()))
                .bodyValue(albumRequest)
                .retrieve()
                .bodyToMono(AlbumResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }

    public void deleteAlbum(Long id) {
        jsonPlaceHolderClient
                .method(HttpMethod.DELETE)
                .uri(String.join("/", ALBUM_URL, id.toString()))
                .retrieve();
    }
}
