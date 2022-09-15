package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class RunAfterBothExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> "Both").runAfterBoth(CompletableFuture.supplyAsync(() -> "Message"), () -> {
            log.info("done");
        });
        System.out.println("result : " + cf.get());
    }
}
