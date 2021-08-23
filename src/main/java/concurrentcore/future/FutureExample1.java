package concurrentcore.future;

import java.util.concurrent.*;

public class FutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Double> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 53.5d;
        });
        System.out.println("main thread do other thing.");
        System.out.println("the task result: " + future.get());
        executor.shutdown();
    }
}
