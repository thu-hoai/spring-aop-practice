package com.example.demo.service;

import com.example.demo.model.Album;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumService.class);

    private final TypiAlbumService typiAlbumService;
    private final LocalAlbumService localAlbumService;

    public AlbumService(TypiAlbumService typiAlbumService, LocalAlbumService localAlbumService) {
        this.typiAlbumService = typiAlbumService;
        this.localAlbumService = localAlbumService;
    }

    @CircuitBreaker(name="getAlbums", fallbackMethod = "handleFallBackMethodGetAlbums")
    public List<Album> getAlbumList() {
        return typiAlbumService.getAlbumsList();
    }

    private List<Album> handleFallBackMethodGetAlbums(Exception exception) {
        LOGGER.error("Failed in connecting to TypiCode", exception);
        LOGGER.info("Start getting albums list from local");
        return localAlbumService.getAlbumsList();
    }
}