package concurrentcore.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample2 {
    public static void main(String[] args) {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                completableFuture.cancel(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            completableFuture.get();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
