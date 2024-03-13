package ru.khakimov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khakimov.audit.annotation.Audit;
import ru.khakimov.dto.response.ListAlbumResponse;
import ru.khakimov.dto.response.ListPostResponse;
import ru.khakimov.service.proxy.JsonPlaceHolderUsersProxy;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAnyRole('ROLE_USERS', 'ROLE_ADMIN')")
@RequiredArgsConstructor
public class UsersController {
    private final JsonPlaceHolderUsersProxy proxy;

    @GetMapping("/{id}/posts")
    @Audit
    public ListPostResponse getUserPosts(@PathVariable("id") Long id) {
        return proxy.getPostsById(id).block();
    }

    @GetMapping("/{id}/albums")
    @Audit
    public ListAlbumResponse getUserAlbums(@PathVariable("id") Long id) {
        return proxy.getAlbumsById(id).block();
    }
}
