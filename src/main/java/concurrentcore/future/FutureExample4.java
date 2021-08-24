package concurrentcore.future;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FutureExample4 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ListeningExecutorService decoratorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<String> listenableFuture = decoratorService.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "I am the result";
        });

        listenableFuture.addListener(() -> {
            System.out.println("The task completed.");
            try {
                System.out.println("The task result: " + listenableFuture.get());
                decoratorService.shutdown();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, decoratorService);
    }
}
