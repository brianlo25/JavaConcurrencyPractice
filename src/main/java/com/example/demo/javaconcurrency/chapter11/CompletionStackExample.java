package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class CompletionStackExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> baseFuture = CompletableFuture.completedFuture("Base Future");
        baseFuture.thenApply(r -> "Then Apply");
        baseFuture.thenAccept(r -> log.info(r)).thenAccept(Void -> log.info("void"));
        baseFuture.thenApply(r -> "Apply Message").thenAccept(r -> log.info("result : " + r));
        log.info("finish : {}", baseFuture.get());

    }
}
