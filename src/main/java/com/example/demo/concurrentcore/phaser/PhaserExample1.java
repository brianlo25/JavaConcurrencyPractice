package com.example.demo.concurrentcore.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PhaserExample1 {
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser();

        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                phaser.register();
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    phaser.arrive();
                    System.out.println(new Date() + ":" + Thread.currentThread() + " complete th work.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "T-" + i).start();
        }

        TimeUnit.SECONDS.sleep(10);
        phaser.register();
        phaser.arriveAndAwaitAdvance();

        assert phaser.getRegisteredParties() == 11 : "total 11 parties is registered.";
        System.out.println(new Date() + ": all of sub task completed.");
    }
}
