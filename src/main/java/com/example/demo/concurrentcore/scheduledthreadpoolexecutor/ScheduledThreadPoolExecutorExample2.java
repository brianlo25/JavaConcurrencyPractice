package com.example.demo.concurrentcore.scheduledthreadpoolexecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

        ScheduledFuture<?> future = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("I am running");
        }, 10, TimeUnit.SECONDS);

        System.out.println(future.get());
    }
}
