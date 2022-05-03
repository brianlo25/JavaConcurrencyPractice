package com.example.demo.javaconcurrency.chapter06.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartUp {
    private static List<BaseHealthChecker> service;
    private static CountDownLatch latch = new CountDownLatch(2);
    private final static ApplicationStartUp INSTANCE = new ApplicationStartUp();

    static {
        service = new ArrayList<BaseHealthChecker>();
        service.add(new CacheHealthChecker(latch));
        service.add(new DatabaseHealthChecker(latch));
    }

    private ApplicationStartUp() {};

    public static ApplicationStartUp getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalService() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(service.size());
        for ( final BaseHealthChecker v : service) {
            executor.execute(v);
        }

        latch.await();

        for ( final BaseHealthChecker v : service) {
            if (!v.isServiceUp()) {
                return false;
            }
        }

        return true;
    }
}
