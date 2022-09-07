package com.example.demo.client;

import com.example.demo.model.Album;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
    value = "json-placeholder",
    url = "${typi-code-client.base-url}")
public interface TypiCodeClient {

    @GetMapping(value = "${typi-code-client.get-album-path}")
    List<Album> getAlbumsList();
}
