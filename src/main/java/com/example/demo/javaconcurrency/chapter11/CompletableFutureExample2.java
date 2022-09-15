package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class CompletableFutureExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> v1 = CompletableFuture.runAsync(() -> {
            log.info("no return value for v1");
        });
        CompletableFuture<Void> v2 = CompletableFuture.runAsync(() -> {
            log.info("no return value for v2");
        });
        CompletableFuture.allOf(v1, v2).join();

        CompletableFuture<String> v3 = CompletableFuture.supplyAsync(() -> {
            return "return value v3";
        });
        CompletableFuture<String> v4 = CompletableFuture.supplyAsync(() -> {
            return "return value v4";
        });
        CompletableFuture.anyOf(v3, v4).thenAccept(value -> log.info(value.toString())).join();

        CompletableFuture<String> cf = CompletableFuture.completedFuture("value");
        log.info(cf.get());
    }
}
