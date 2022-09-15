package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class WhenCompleteExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> cf = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Occur Exception");
        }).whenComplete((r, th) -> {
            if (th != null) {
                log.error("前置任務出現異常");
            } else {
                log.info("前置任務正常");
            }
        });
        log.info((String) cf.get());
    }
}
