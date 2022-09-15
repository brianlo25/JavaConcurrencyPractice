package com.example.demo.javaconcurrency.chapter11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class ThenAcceptExample {
    public static void main(String[] args) {
        CompletableFuture<Void> cf1 = CompletableFuture.supplyAsync(() -> "remote result").thenAccept(result -> {
            log.info("第一個異步任務的返回值 : " + result);
            log.info("把result保存到資料庫");
        });

        CompletableFuture<Void> cf2 = CompletableFuture.supplyAsync(() -> "thanAccept message").thenAcceptAsync(result -> {
            log.info(Thread.currentThread().getName() + "第一個異步任務返回值 : " + result);
        });
    }
}
