package concurrentcore.concurrentqueue;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentLinkedQueueMemLeak {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
        queue.add(new Object());
        Object object = new Object();

        int loops = 0;

        TimeUnit.SECONDS.sleep(10);

        Stopwatch stopwatch = Stopwatch.createStarted();
        while (true) {
            if (loops % 10000 == 0 && loops != 0) {
                long elapsedMs = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                System.out.printf("loops=%d duration=%d MS%n", loops, elapsedMs);
                stopwatch.reset().start();
            }
            queue.add(object);
            queue.remove(object);
            ++loops;
        }
    }
}
