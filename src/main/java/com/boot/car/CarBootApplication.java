package com.boot.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarBootApplication.class, args);
        System.out.println("Hello CarBootApplication");
    }

}
