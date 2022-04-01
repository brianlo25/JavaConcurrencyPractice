package javaconcurrency.chapter06.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo implements Runnable{
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadName:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new CountDownLatchDemo(), "t" + i).start();
        }
        countDownLatch.countDown();
        System.out.println("ThreadName:" + Thread.currentThread().getName());
    }
}
