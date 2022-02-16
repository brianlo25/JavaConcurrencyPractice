package javaconcurrency.chapter04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    static Lock lock = new ReentrantLock();
    private int count = 0;
    public void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        Thread[] threads = new Thread[2];
        for (int j = 0; j < 2; j++) {
            threads[j] = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    reentrantLockExample.incr();
                }
            });
            threads[j].start();
        }
        threads[0].join();
        threads[1].join();
        System.out.println(reentrantLockExample.count);
    }
}
