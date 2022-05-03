package com.example.demo.javaconcurrency.chapter06.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker{
    public CacheHealthChecker(CountDownLatch latch) {
        super(latch, "CacheHealthChecker");
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is UP");
    }
}
