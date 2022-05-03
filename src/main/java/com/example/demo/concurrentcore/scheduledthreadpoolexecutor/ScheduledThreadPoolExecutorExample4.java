package com.example.demo.concurrentcore.scheduledthreadpoolexecutor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorExample4 {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

        Runnable command = () -> {
            long startTimeStamp = System.currentTimeMillis();
            System.out.println("current timestamp: " + startTimeStamp);
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("elapsed time: " + (System.currentTimeMillis() - startTimeStamp));
        };

//        scheduledThreadPoolExecutor.scheduleAtFixedRate(command, 10 ,1000, TimeUnit.MILLISECONDS);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(command, 10 ,1000, TimeUnit.MILLISECONDS);
    }
}
