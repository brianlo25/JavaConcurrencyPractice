package concurrentcore.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample1 {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " acquired th lock.");
                System.out.println(lock.getHoldCount() == 1);
                lock.lock();
                System.out.println(Thread.currentThread() + " acquired th lock again.");
                System.out.println(lock.getHoldCount() == 2);
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread() + " released th lock.");
                System.out.println(lock.getHoldCount() == 1);
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        lock.lock();
        System.out.println("main thread acquired th lock.");
        lock.unlock();
        System.out.println("main thread released th lock.");
    }
}
