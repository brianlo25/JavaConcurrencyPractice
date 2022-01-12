package javaconcurrency.chapter02;

public class SynchronizedForObjectExample {
    Object lock = new Object();

    public void m1() {
        synchronized (lock) {
            while (true) {
                System.out.println("當前獲得鎖的線程 : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedForObjectExample se1 = new SynchronizedForObjectExample();
        SynchronizedForObjectExample se2 = new SynchronizedForObjectExample();
        new Thread(() -> se1.m1(), "t1").start();
        new Thread(() -> se2.m1(), "t2").start();
    }
}
