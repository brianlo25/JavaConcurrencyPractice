package concurrentcore.ratelimiter;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RateLimiterTokenBucket {
    private final static int MAX = 1000;

    private int orderId;

    private final RateLimiter rateLimiter = RateLimiter.create(10.0D);

    private Monitor bookOrderMonitor = new Monitor();

    static class NoProductionException extends Exception {
        public NoProductionException(String message) {
            super(message);
        }
    }

    static class OrderFailedException extends Exception {
        public OrderFailedException(String message) {
            super(message);
        }
    }

    public void bookOrder(Consumer<Integer> consumer) throws OrderFailedException, NoProductionException {
        if (bookOrderMonitor.enterIf(bookOrderMonitor.newGuard(() -> orderId < MAX))) {
            try {
                if (!rateLimiter.tryAcquire(100, TimeUnit.MILLISECONDS)){
                    throw new OrderFailedException("book order failed, please try again later");
                }
                orderId++;
                consumer.accept(orderId);
            } finally {
                bookOrderMonitor.leave();
            }
        } else {
            throw new NoProductionException("no available production now.");
        }
    }
}
