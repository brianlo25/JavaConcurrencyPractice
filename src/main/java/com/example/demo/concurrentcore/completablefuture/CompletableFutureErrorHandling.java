package com.example.demo.concurrentcore.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureErrorHandling {
    public static void main(String[] args) {
        CompletableFuture.<String>supplyAsync(() -> {
            throw new RuntimeException();
        }).handle((r, e) -> {
            if (e != null) {
                return "ERROR";
            } else {
                return r;
            }
        }).thenAccept(System.out::println);
    }
}
