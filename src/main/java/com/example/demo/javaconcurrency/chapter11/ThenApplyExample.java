package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ThenApplyExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Combine").thenCombine(CompletableFuture.supplyAsync(() -> "message"), (r1, r2) -> {
            log.info("result : " + (r1 + " " + r2));
            return r1 + " " + r2;
        });
        System.out.println(cf.get());
    }
}
