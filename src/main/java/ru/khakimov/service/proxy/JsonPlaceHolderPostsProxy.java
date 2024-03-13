package ru.khakimov.service.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.khakimov.dto.request.PostRequest;
import ru.khakimov.dto.response.ListPostResponse;
import ru.khakimov.dto.response.PostResponse;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JsonPlaceHolderPostsProxy {
    private static final String POST_URL = "/posts";
    private static final long CACHE_DURATION = 10;
    private final WebClient jsonPlaceHolderClient;

    public Mono<PostResponse> getPostById(Long id) {
        return jsonPlaceHolderClient
                .get()
                .uri(String.join("/", POST_URL, id.toString()))
                .retrieve()
                .bodyToMono(PostResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));
    }

    public Mono<ListPostResponse> getAll() {
        return jsonPlaceHolderClient
                .get()
                .uri(POST_URL)
                .retrieve()
                .bodyToFlux(PostResponse.class)
                .collectList()
                .map(ListPostResponse::new)
                .cache(Duration.ofMinutes(CACHE_DURATION));
    }

    public Mono<PostResponse> createPost(PostRequest postRequest) {
        return jsonPlaceHolderClient
                .post()
                .uri(POST_URL)
                .bodyValue(postRequest)
                .retrieve()
                .bodyToMono(PostResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }

    public Mono<PostResponse> updatePost(Long id, PostRequest postRequest) {
        return jsonPlaceHolderClient
                .put()
                .uri(String.join("/", POST_URL, id.toString()))
                .bodyValue(postRequest)
                .retrieve()
                .bodyToMono(PostResponse.class)
                .onErrorResume(error -> Mono.empty())
                .cache(Duration.ofMinutes(CACHE_DURATION));

    }

    public void deletePost(Long id) {
        jsonPlaceHolderClient
                .method(HttpMethod.DELETE)
                .uri(String.join("/", POST_URL, id.toString()))
                .retrieve();
    }
}
