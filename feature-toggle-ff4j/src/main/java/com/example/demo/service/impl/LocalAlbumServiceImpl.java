package com.example.demo.service.impl;

import com.example.demo.model.Album;
import com.example.demo.service.LocalAlbumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LocalAlbumServiceImpl implements LocalAlbumService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocalAlbumServiceImpl.class);

  @Override
  public List<Album> getAlbumsList() {
    try {
      return deserializeJsonString(
          new String(
              Files.readAllBytes(
                  Paths.get(getClass().getClassLoader().getResource("album-list.json").toURI()))));
    } catch (Exception e) {
      LOGGER.error("error occurred while reading the file", e);
    }
    return new ArrayList<>();
  }

  private List<Album> deserializeJsonString(String jsonString) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    List<Album> albums = new ArrayList<>();
    try {
      albums = objectMapper.readValue(jsonString, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return albums;
  }
}
