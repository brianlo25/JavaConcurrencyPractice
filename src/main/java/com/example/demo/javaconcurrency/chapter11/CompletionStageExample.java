package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class CompletionStageExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf = CompletableFuture
                .supplyAsync(() -> "thenAccept message")
                .thenAcceptAsync(result -> log.info(Thread.currentThread().getName() + "第一個一步任務返回值 : " + result));
        cf.get();
    }
}
