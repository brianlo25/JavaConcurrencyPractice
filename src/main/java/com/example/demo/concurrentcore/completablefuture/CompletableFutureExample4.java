package com.example.demo.concurrentcore.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample4 {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Java")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Scala"));

        completableFuture.thenApply(String::toUpperCase).thenAccept(System.out::println);

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Java")
                .thenCombine(CompletableFuture.supplyAsync(() -> " Scala"), (s1, s2) -> s1 + s2);

        completableFuture2.thenApply(String::toUpperCase).thenAccept(System.out::println);
    }
}
