package concurrentcore.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync:" + Thread.currentThread());
            return "Java";
        }, executor).thenAccept(v -> {
            System.out.println("thanAccept:" + Thread.currentThread());
            System.out.println(v);
        }).thenRun(() -> {
            System.out.println("All of task completed." + Thread.currentThread());
        });

        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync:" + Thread.currentThread());
            return "Java";
        }, executor).thenAcceptAsync(v -> {
            System.out.println("thanAcceptAsync:" + Thread.currentThread());
            System.out.println(v);
        }).thenRunAsync(() -> {
            System.out.println("All of task completed." + Thread.currentThread());
        }, executor);
        executor.shutdown();
    }
}
