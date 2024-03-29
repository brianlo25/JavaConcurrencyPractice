package com.example.demo.concurrency.chapter08;

public class InternalTask implements Runnable{
    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }


    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = runnableQueue.take();
            } catch (Exception e) {
               running = false;
               break;
            }
        }
    }

    public void stop(){
        this.running = false;
    }
}
