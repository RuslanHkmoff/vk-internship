package ru.khakimov.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.khakimov.audit.annotation.Audit;
import ru.khakimov.dto.request.AlbumRequest;
import ru.khakimov.dto.response.AlbumResponse;
import ru.khakimov.dto.response.ListAlbumResponse;
import ru.khakimov.service.proxy.JsonPlaceHolderAlbumsProxy;

@RestController
@RequestMapping("api/albums")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ALBUMS', 'ROLE_ADMIN')")
public class AlbumsController {
    private final JsonPlaceHolderAlbumsProxy proxy;

    @GetMapping(value = "/{id}")
    @Audit
    public AlbumResponse getAlbum(@PathVariable("id") @Positive Long id) {
        return proxy.getAlbumById(id).block();
    }

    @GetMapping(value = "")
    @Audit
    public ListAlbumResponse getAllAlbums() {
        return proxy.getAll().block();
    }

    @PostMapping(value = "")
    @Audit
    public AlbumResponse createAlbum(@RequestBody @Valid AlbumRequest request) {
        return proxy.createAlbum(request).block();
    }

    @PutMapping(value = "/{id}")
    @Audit
    public AlbumResponse updateAlbum(@PathVariable("id") @Positive Long id,
                                     @RequestBody @Valid AlbumRequest request) {
        return proxy.updateAlbum(id, request).block();
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteAlbum(@PathVariable("id") @Positive Long id) {
        proxy.deleteAlbum(id);
    }
}
