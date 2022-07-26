package com.example.demo.aop.logger.annotation;

import com.example.demo.aop.logger.Logger;
import com.example.demo.aop.logger.NameRepository;
import com.example.demo.aop.logger.LoggerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import({LoggerAspect.class, NameRepository.class})
public class Config {
    @Bean
    public Logger logger() {
        return (message) -> System.out.println(message);
    }
}