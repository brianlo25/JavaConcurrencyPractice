package com.example.demo.javaconcurrency.chapter06.semaphore;

import java.util.concurrent.*;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            service.execute(new SomeTask(semaphore));
        }
    }

    static class SomeTask implements Runnable {
        private Semaphore semaphore;

        public SomeTask(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "獲得一個令牌");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + "釋放一個令牌");
                semaphore.release();
            }
        }
    }
}
