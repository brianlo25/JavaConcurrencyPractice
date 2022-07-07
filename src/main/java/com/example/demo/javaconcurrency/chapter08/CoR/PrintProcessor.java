package com.example.demo.javaconcurrency.chapter08.CoR;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class PrintProcessor extends Thread implements IRequestProcessor {
    BlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    protected IRequestProcessor nextProcessor;

    public PrintProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
       while (true) {
           try {
               Request request = requests.take();
               log.info("PrintProcessor:" + request);
               if (null != nextProcessor) {
                   nextProcessor.processRequest(request);
               }
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
