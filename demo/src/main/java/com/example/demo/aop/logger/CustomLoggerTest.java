package com.example.demo.aop.logger;

import com.example.demo.aop.logger.annotation.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomLoggerTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
        config.register(Config.class);
        config.refresh();

        NameRepository repository = config.getBean(NameRepository.class);
        repository.getNames();
    }
}
