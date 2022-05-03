package com.example.demo.javaconcurrency.chapter07.threadLocal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MemoryLeakExample {
    static class LocalVariable {
        private Long[] data = new Long[1024 * 1024];
    }

    static ThreadPoolExecutor service = new ThreadPoolExecutor(5, 5, 80, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    static ThreadLocal local = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(8);
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            service.execute(() -> {
                local.set(new LocalVariable());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                local.remove();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        local = null;
        System.gc();
        System.out.println(local);
    }
}
