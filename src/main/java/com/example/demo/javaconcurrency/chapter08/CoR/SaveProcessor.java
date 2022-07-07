package com.example.demo.javaconcurrency.chapter08.CoR;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class SaveProcessor extends Thread implements IRequestProcessor {
    BlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    @Override
    public void run() {
       while (true) {
           try {
               Request request = requests.take();
               log.info("SaveProcessor:" + request);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }
}
