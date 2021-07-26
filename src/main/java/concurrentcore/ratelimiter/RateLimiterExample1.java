package concurrentcore.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterExample1 {
    private static RateLimiter rateLimiter = RateLimiter.create(0.5);

    private static void testRateLimiter() {
        double elapsedSecond = rateLimiter.acquire();
        System.out.println(Thread.currentThread() + ": elapsed seconds: " + elapsedSecond);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread(RateLimiterExample1::testRateLimiter).start();
        }
    }
}
