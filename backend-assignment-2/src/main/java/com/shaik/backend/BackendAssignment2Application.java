package com.shaik.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendAssignment2Application {

    public static void main(String[] args) {

   
        System.setProperty("user.timezone", "UTC");

        SpringApplication.run(BackendAssignment2Application.class, args);
    }
}