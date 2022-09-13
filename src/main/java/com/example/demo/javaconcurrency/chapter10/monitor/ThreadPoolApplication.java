package com.example.demo.javaconcurrency.chapter10.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ThreadPoolConfigureProperties.class)
@SpringBootApplication
public class ThreadPoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolApplication.class, args);
    }
}
