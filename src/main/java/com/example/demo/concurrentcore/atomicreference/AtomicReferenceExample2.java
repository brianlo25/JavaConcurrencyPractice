package com.example.demo.concurrentcore.atomicreference;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AtomicReferenceExample2 {
    static volatile DebitCard debitCard = new DebitCard("Alex", 0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread("T-" + i){
                @Override
                public void run() {
                   while (true){
                       synchronized (AtomicReferenceExample2.class){
                           final DebitCard dc = debitCard;
                           DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                           System.out.println(newDC);
                           debitCard = newDC;
                       }

                       try {
                           TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }.start();
        }
    }
}
