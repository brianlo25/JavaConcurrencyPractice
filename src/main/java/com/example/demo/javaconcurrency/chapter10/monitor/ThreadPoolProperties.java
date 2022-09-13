package com.example.demo.javaconcurrency.chapter10.monitor;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class ThreadPoolProperties {
    private String poolName;
    private int corePoolSize;
    private int maximumPoolSize = Runtime.getRuntime().availableProcessors();
    private long keepAliveTime = 60;
    private TimeUnit unit = TimeUnit.SECONDS;
    private int queueCapacity = Integer.MAX_VALUE;
}
