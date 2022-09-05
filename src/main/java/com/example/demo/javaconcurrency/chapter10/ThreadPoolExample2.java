package com.example.demo.javaconcurrency.chapter10;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample2 {
    private static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(2, 6, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20));

    public static void executeTask() {
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        printPollStatus("before");
        threadPoolExecutor.setCorePoolSize(7);
        threadPoolExecutor.setMaximumPoolSize(14);
        printPollStatus("after");
    }

    public static void printPollStatus(String name) {
        System.out.println("==========" + name + "==========");
        LinkedBlockingQueue queue = (LinkedBlockingQueue) threadPoolExecutor.getQueue();
        System.out.println("核心線程數 : " + threadPoolExecutor.getCorePoolSize() +
                " 最大線程數量 : " + threadPoolExecutor.getMaximumPoolSize() +
                " 隊列中任務個數 : " + queue.size());
    }

    public static void main(String[] args) {
        executeTask();
    }
}
