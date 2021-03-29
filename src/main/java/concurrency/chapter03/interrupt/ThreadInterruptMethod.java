package concurrency.chapter03.interrupt;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptMethod {
    public static void main(String[] args) {
        System.out.println("Main thread is interrupted? " + Thread.interrupted());

        Thread.currentThread().interrupt();

        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupt still");
        }
    }
}
