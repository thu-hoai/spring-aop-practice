package com.example.demo.service;

public interface FeatureToggleService {

  boolean isFeatureEnabled(String featureUid);
}
