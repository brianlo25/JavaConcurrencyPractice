package com.example.demo.concurrentcore.future;

import java.util.concurrent.*;

public class FutureExample2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Double> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task completed.");
            return 53.5d;
        });
        TimeUnit.SECONDS.sleep(10);
        System.out.println("cancel success ? " + future.cancel(false));
        System.out.println("future is canceled ? " + future.isCancelled());
        System.out.println("the task result: " + future.get());
    }
}
