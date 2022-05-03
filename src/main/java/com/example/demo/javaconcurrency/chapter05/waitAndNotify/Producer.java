package com.example.demo.javaconcurrency.chapter05.waitAndNotify;

import java.util.Queue;

public class Producer implements Runnable{
    private Queue<String> bags;
    private int maxSize;

    public Producer(Queue<String> bags, int maxSize) {
        this.bags = bags;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            synchronized (bags) {
                if (bags.size() == maxSize) {
                    System.out.println("bags 滿了");
                    try {
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生產者生產 : bag" + i);
                bags.add("bag" + i);
                bags.notify();
            }
        }
    }
}
