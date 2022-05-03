package com.example.demo.concurrentcore.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureExample3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            callables.add(() -> {
                int random = ThreadLocalRandom.current().nextInt(30);
                TimeUnit.SECONDS.sleep(random);
                System.out.println("Task: " + random + " complete in Thread " + Thread.currentThread());
                return random;
            });
        }
//        Integer result = executor.invokeAny(callables);
//        System.out.println("Result: " + result);

        try {
            List<Future<Integer>> futures = executor.invokeAll(callables);
            futures.forEach(future -> {
                try {
                    System.out.println("Result: " + future.get());
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
