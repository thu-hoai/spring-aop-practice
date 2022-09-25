package com.example.demo.service;

import static com.example.demo.consts.FeatureName.GET_ALBUMS_LIST;

import com.example.demo.model.Album;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlbumService.class);

  private final TypiAlbumService typiAlbumService;
  private final LocalAlbumService localAlbumService;
  private final FeatureToggleService featureToggleService;

  public AlbumService(
      TypiAlbumService typiAlbumService,
      LocalAlbumService localAlbumService,
      FeatureToggleService featureToggleService) {
    this.typiAlbumService = typiAlbumService;
    this.localAlbumService = localAlbumService;
    this.featureToggleService = featureToggleService;
  }

  public List<Album> getAlbumList() {
    if (featureToggleService.isFeatureEnabled(GET_ALBUMS_LIST)) {
      LOGGER.info("Getting albums list from TYPI");
      return typiAlbumService.getAlbumsList();
    }
    LOGGER.info("Getting albums list from local");
    return localAlbumService.getAlbumsList();
  }
}
