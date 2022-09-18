package com.example.demo.config;

import javax.sql.DataSource;
import org.ff4j.FF4j;
import org.ff4j.core.FeatureStore;
import org.ff4j.security.SpringSecurityAuthorisationManager;
import org.ff4j.springjdbc.store.EventRepositorySpringJdbc;
import org.ff4j.springjdbc.store.FeatureStoreSpringJdbc;
import org.ff4j.springjdbc.store.PropertyStoreSpringJdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4JConfig {

  @Bean
  public FF4j configFF4J(DataSource dataSource) {
    FF4j ff4j = new FF4j();
    ff4j.setAuthorizationsManager(new SpringSecurityAuthorisationManager());

    FeatureStore featureStore = new FeatureStoreSpringJdbc(dataSource);
    ff4j.setFeatureStore(featureStore);
    ff4j.setPropertiesStore(new PropertyStoreSpringJdbc(dataSource));
    ff4j.setEventRepository(new EventRepositorySpringJdbc(dataSource));
    ff4j.audit();
    return ff4j;
  }
}
