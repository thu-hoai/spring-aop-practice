package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.service.impl.LocalAlbumServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocalAlbumServiceTest {

    @Autowired
    private LocalAlbumServiceImpl service;

    @Test
    void testLoadResource() {
        List<Album> albums = service.getAlbumsList();
        assertEquals(2, albums.size());
    }
}
