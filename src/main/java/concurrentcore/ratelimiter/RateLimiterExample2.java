package concurrentcore.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterExample2 {
    private static RateLimiter rateLimiter = RateLimiter.create(2.0);

    public static void main(String[] args) {
        System.out.println(rateLimiter.acquire(4));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(2));
    }
}
