package com.example.demo.concurrentcore.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample3 {
    private static int shareData = 0;

    private static final StampedLock lock = new StampedLock();

    public static void inc() {
        long stamp = lock.writeLock();
        try {
            shareData++;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static int get() {
        long stamp = lock.tryOptimisticRead();
        if (!lock.validate(stamp)){
            stamp = lock.readLock();
            try {
                return shareData;
            } finally {
                lock.unlockRead(stamp);
            }

        }
        return shareData;
    }
}
