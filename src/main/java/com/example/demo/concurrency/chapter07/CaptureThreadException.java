package com.example.demo.concurrency.chapter07;

import java.util.concurrent.TimeUnit;

public class CaptureThreadException {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e) ->{
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }

            System.out.println(1/0);
        },"Test-Thread");

        thread.start();
    }
}
