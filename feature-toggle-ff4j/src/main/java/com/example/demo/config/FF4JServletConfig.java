package com.example.demo.config;

import org.ff4j.FF4j;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4JServletConfig {

  @Bean
  public ServletRegistrationBean ff4jDispatcherServletRegistrationBean(
      FF4jDispatcherServlet servlet) {
    return new ServletRegistrationBean(servlet, "/ff4j-web-console/*");
  }

  @Bean
  public FF4jDispatcherServlet getFF4jDispatcherServlet(FF4j ff4j) {
    var ff4jConsoleServlet = new FF4jDispatcherServlet();
    ff4jConsoleServlet.setFf4j(ff4j);
    return ff4jConsoleServlet;
  }
}
