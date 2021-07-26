package concurrentcore.ratelimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterExample3 {
    private static final AtomicInteger data = new AtomicInteger(0);
    private static final RateLimiterBucket bucket = new RateLimiterBucket();

    public static void main(String[] args) {
        for (int i = 0; i< 10; i++) {
            new Thread(() -> {
                while (true) {
                    bucket.submitRequest(data.getAndIncrement());
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i< 10; i++) {
            new Thread(() -> {
                while (true) {
                   bucket.handleRequest(System.out::println);
                }
            }).start();
        }
    }
}
