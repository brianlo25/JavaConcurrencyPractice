package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ExceptionallyExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> cf = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Occur Exception");
        }).exceptionally(e -> {
            log.error("{}", e.getMessage());
            return "ExceptionallyExample";
        });
        log.info((String) cf.get());
    }
}
