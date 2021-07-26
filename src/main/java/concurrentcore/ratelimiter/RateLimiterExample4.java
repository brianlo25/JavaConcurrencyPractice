package concurrentcore.ratelimiter;

public class RateLimiterExample4 {
    private static final RateLimiterTokenBucket tokenBucket = new RateLimiterTokenBucket();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        tokenBucket.bookOrder(proId -> System.out.println("User: " + Thread.currentThread() + " book the prod and prodId:" + proId));
                    } catch (RateLimiterTokenBucket.NoProductionException e) {
                        System.out.println("all of production already sold out.");
                    } catch (RateLimiterTokenBucket.OrderFailedException e) {
                        System.out.println("User: " + Thread.currentThread() + " book order failed, will try again.");
                    }
                }
            }).start();
        }
    }
}
