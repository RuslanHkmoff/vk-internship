package ru.khakimov.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.khakimov.audit.annotation.Audit;
import ru.khakimov.dto.request.PostRequest;
import ru.khakimov.dto.response.ListPostResponse;
import ru.khakimov.dto.response.PostResponse;
import ru.khakimov.service.proxy.JsonPlaceHolderPostsProxy;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_POSTS', 'ROLE_ADMIN')")
public class PostsController {
    private final JsonPlaceHolderPostsProxy proxy;

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable("id") @Positive Long id) {
        return proxy.getPostById(id).block();
    }

    @GetMapping()
    @Audit
    public ListPostResponse getAllPosts() {
        return proxy.getAll().block();
    }

    @PostMapping()
    @Audit
    public PostResponse createPost(@RequestBody @Valid PostRequest request) {
        return proxy.createPost(request).block();
    }

    @DeleteMapping("/{id}")
    @Audit
    public void deletePost(@PathVariable("id") @Positive Long id) {
        proxy.deletePost(id);
    }

    @PutMapping("/{id}")
    @Audit
    public PostResponse updatePost(@PathVariable("id") @Positive Long id,
                                   @RequestBody @Valid PostRequest request) {
        return proxy.updatePost(id, request).block();
    }
}
