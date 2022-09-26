package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.config.strategy.OfficeHoursFlippingStrategy;
import java.util.Calendar;
import java.util.Map;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FlippingStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class OfficeHoursFlippingStrategyTest {

  private static final String GET_ALBUMS_LIST = "getAlbumList";
  private static final String TEST_DASHBOARD_DATA_FILE_NAME = "/test-data/feature.sql";

  @Spy private FF4j ff4j;

  @Test
  void testStrategy() {
    // given
    OfficeHoursFlippingStrategy officeHoursFlippingStrategy = new OfficeHoursFlippingStrategy();
    officeHoursFlippingStrategy.init(GET_ALBUMS_LIST, Map.of("startDate", "9", "endDate", "18"));
    Feature mockFeature = new Feature(GET_ALBUMS_LIST, true);
    mockFeature.setFlippingStrategy(officeHoursFlippingStrategy);
    ff4j.createFeature(mockFeature);

    // when then
    FlippingStrategy flippingStrategy = ff4j.getFeature(GET_ALBUMS_LIST).getFlippingStrategy();
    assertTrue(flippingStrategy.getClass() == OfficeHoursFlippingStrategy.class);
    assertEquals("9", flippingStrategy.getInitParams().get("startDate"));
    int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    boolean isNowOfficeTime = (hour >= 9) && (hour < 18);
    assertEquals(isNowOfficeTime, ff4j.check(GET_ALBUMS_LIST));
  }
}
