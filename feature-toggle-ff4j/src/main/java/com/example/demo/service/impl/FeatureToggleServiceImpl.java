package com.example.demo.service.impl;

import com.example.demo.service.FeatureToggleService;
import org.ff4j.FF4j;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleServiceImpl implements FeatureToggleService {

  private final FF4j ff4j;

  public FeatureToggleServiceImpl(FF4j ff4j) {
    this.ff4j = ff4j;
  }

  @Override
  public boolean isFeatureEnabled(String featureUid) {
    boolean isEnabled;
    try {
      isEnabled = ff4j.check(featureUid);
    } catch (Exception e) {
      isEnabled = false;
    }
    return isEnabled;
  }
}
