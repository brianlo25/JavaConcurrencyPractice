package concurrentcore.threadpoolexecutor;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        executor.execute(() -> System.out.println("execute runnable task"));

        Future<String> future = executor.submit(() -> "Execute the callable task and this is result");

        System.out.println(future.get());
    }
}
