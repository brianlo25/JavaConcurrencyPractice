package concurrentcore.completionservice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample1 {

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        final List<Callable<Integer>> tasks = Arrays.asList(
                () -> {
                    sleep(30);
                    System.out.println("Task 30 completed done.");
                    return 30;
                },
                () -> {
                    sleep(10);
                    System.out.println("Task 10 completed done.");
                    return 10;
                },
                () -> {
                    sleep(20);
                    System.out.println("Task 20 completed done.");
                    return 20;
                }
                );

        tasks.forEach(completionService::submit);
        for (int i = 0; i < tasks.size(); i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
