package concurrency.chapter19;

import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();

        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finish done");
        });

        future.get();
    }
}
