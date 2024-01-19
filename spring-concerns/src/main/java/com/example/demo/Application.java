package com.example.demo;

import com.example.demo.circulardependencies.Car;
import com.example.demo.circulardependencies.Driver;
import com.example.demo.n1problem.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserService userService = applicationContext.getBean(UserService.class);
//        var users = userService.getUsers();
//        System.out.println(users);

        var accounts = userService.getAccounts();
        System.out.println(accounts);

//        var accounts1 = userService.getAccounts1();
//        System.out.println(accounts1);
    }
}
