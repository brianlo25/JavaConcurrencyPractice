package javaconcurrency.chapter05.waitAndNotify;

import java.util.Queue;

public class Consumer implements Runnable{
    private Queue<String> bags;
    private int maxSize;

    public Consumer(Queue<String> bags, int maxSize) {
        this.bags = bags;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bags) {
                if (bags.isEmpty()) {
                    System.out.println("bags為空");
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
                String bag = bags.remove();
                System.out.println("消費者消費 : " + bag);
                bags.notify();
            }
        }
    }
}
