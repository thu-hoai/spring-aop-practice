package com.example.demo;

import com.example.demo.circulardependencies.Car;
import com.example.demo.circulardependencies.Driver;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
