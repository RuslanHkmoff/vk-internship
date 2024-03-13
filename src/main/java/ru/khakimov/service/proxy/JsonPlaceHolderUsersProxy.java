package ru.khakimov.service.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.khakimov.dto.response.ListAlbumResponse;
import ru.khakimov.dto.response.ListPostResponse;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JsonPlaceHolderUsersProxy {
    private static final String USERS_URL = "/users";
    private static final String POSTS_URL = "/posts";
    private static final String ALBUMS_URL = "/albums";
    private static final long CACHE_DURATION = 10;
    private final WebClient jsonPlaceHolderClient;

    public Mono<ListPostResponse> getPostsById(Long id) {
        return jsonPlaceHolderClient
                .get()
                .uri(String.join("/", USERS_URL, id.toString(), POSTS_URL))
                .retrieve()
                .bodyToMono(ListPostResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }

    public Mono<ListAlbumResponse> getAlbumsById(Long id) {
        return jsonPlaceHolderClient
                .get()
                .uri(String.join("/", USERS_URL, id.toString(), ALBUMS_URL))
                .retrieve()
                .bodyToMono(ListAlbumResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }
}
