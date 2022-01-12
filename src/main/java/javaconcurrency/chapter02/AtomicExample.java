package javaconcurrency.chapter02;

public class AtomicExample {
    volatile int i = 0;
    public synchronized void inc() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicExample atomicExample = new AtomicExample();
        Thread[] threads = new Thread[2];
        for (int j = 0; j < 2; j++) {
            int finalJ = j;
            threads[j] = new Thread(() -> {
                for (int k = 0; k< 10000; k++) {
                    atomicExample.inc();
                }
            });
            threads[j].start();
        }
        threads[0].join();
        threads[1].join();
        System.out.println(atomicExample.i);
    }
}
