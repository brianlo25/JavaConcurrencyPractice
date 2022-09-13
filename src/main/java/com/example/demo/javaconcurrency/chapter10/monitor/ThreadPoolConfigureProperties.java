package com.example.demo.javaconcurrency.chapter10.monitor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "monitor.thradpool")
@Data
public class ThreadPoolConfigureProperties {
    private List<ThreadPoolProperties> executors = new ArrayList<>();
}
