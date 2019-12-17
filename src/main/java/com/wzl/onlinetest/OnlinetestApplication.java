package com.wzl.onlinetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.wzl.onlinetest"})
public class OnlinetestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinetestApplication.class, args);
    }

}
