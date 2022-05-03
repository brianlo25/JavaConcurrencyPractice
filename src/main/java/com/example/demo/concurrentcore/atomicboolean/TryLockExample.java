package com.example.demo.concurrentcore.atomicboolean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TryLockExample {

    private final static Object VAL_OBJ = new Object();

    public static void main(String[] args) {

        final TryLock lock = new TryLock();
        final List<Object> validation = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                while (true){
                    try {
                        if (lock.tryLock()){
                            System.out.println(Thread.currentThread() + " : get the lock.");
                            if (validation.size() > 1){
                                throw new IllegalStateException("validation failed.");
                            }
                            validation.add(VAL_OBJ);
                            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        } else {
                            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock.release()){
                            System.out.println(Thread.currentThread() + " : release the lock.");
                            validation.remove(VAL_OBJ);
                        }
                    }
                }
            }).start();
        }
    }
}
