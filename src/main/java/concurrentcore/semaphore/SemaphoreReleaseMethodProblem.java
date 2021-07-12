package concurrentcore.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreReleaseMethodProblem {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1, true);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("The thread t1 acquire permit from semaphore.");
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("The thread t1 is interrupted.");
            } finally {
                semaphore.release();
            }
        });

        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                System.out.println("The thread t2 is interrupted.");
                // 若出現異常，不再往下運行
                return;
            }

            try {
                System.out.println("The thread t2 acquire permit from semaphore.");
            } finally {
                semaphore.release();
            }
        });

        t2.start();

        TimeUnit.SECONDS.sleep(2);

        t2.interrupt();

        semaphore.acquire();

        System.out.println("the main thread acquired permit");
    }
}
