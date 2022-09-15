package com.example.demo.javaconcurrency.chapter11;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompleteMethodExample {
    static class ClientThread implements Runnable {
        private CompletableFuture completableFuture;

        public ClientThread(CompletableFuture completableFuture) {
            this.completableFuture = completableFuture;
        }

        @SneakyThrows
        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + " : " + completableFuture.get());
        }
    }

    public static void main(String[] args) {
        CompletableFuture cf = new CompletableFuture();
        new Thread(new ClientThread(cf)).start();
        new Thread(new ClientThread(cf)).start();
        log.info("兩個客戶端線程被get阻塞");
        cf.complete("finish");
    }
}
