package com.example.demo.concurrentcore.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample2 {

    private static void simulateWork(){
        try {
            System.out.println(Thread.currentThread() + "get the lock and do working...");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class TryLock{
        private final Semaphore semaphore = new Semaphore(1);

        public boolean tryLock(){
            return semaphore.tryAcquire();
        }

        public void unlock(){
            semaphore.release();
            System.out.println(Thread.currentThread() + "release lock");
        }
    }

    public static void main(String[] args) {
        final TryLock tryLock = new TryLock();

        new Thread(() -> {
            boolean getLock = tryLock.tryLock();

            if (!getLock){
                System.out.println(Thread.currentThread() + "can't get the lock, will do other thing.");
                return;
            }

            try {
                simulateWork();
            } finally {
                tryLock.unlock();
            }
        }).start();

        boolean getLock = tryLock.tryLock();

        if (!getLock){
            System.out.println(Thread.currentThread() + "can't get the lock, will do other thing.");
            return;
        }

        try {
            simulateWork();
        } finally {
            tryLock.unlock();
        }
    }
}
