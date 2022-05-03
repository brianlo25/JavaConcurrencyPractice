package com.example.demo.concurrentcore.future;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FutureExample5 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ListeningExecutorService decoratorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<String> listenableFuture = decoratorService.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "I am the result";
        });

        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                System.out.println("The task completed and result: " + s);
                decoratorService.shutdown();
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, decoratorService);
    }
}
