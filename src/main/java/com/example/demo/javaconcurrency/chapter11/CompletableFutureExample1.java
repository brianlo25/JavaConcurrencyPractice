package com.example.demo.javaconcurrency.chapter11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " : 執行一個異步任務");
        });
        completableFuture.get();

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            return "supplyAsync-Future";
        });
        System.out.println(completableFuture2.get());
    }
}
