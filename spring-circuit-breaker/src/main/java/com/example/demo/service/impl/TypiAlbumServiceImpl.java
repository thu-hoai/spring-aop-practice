package com.example.demo.service.impl;

import com.example.demo.client.TypiCodeClient;
import com.example.demo.model.Album;
import com.example.demo.service.TypiAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypiAlbumServiceImpl implements TypiAlbumService {

    private final TypiCodeClient client;

    public TypiAlbumServiceImpl(TypiCodeClient client) {
        this.client = client;
    }

    @Override
    public List<Album> getAlbumsList() {
        return client.getAlbumsList();
    }
}
