package com.example.demo.javaconcurrency.chapter10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(() -> {
            System.out.println("延遲3秒執行的任務");
        }, 3, TimeUnit.SECONDS);
    }
}
