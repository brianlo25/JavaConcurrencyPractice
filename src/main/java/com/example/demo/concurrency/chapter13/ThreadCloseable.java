package com.example.demo.concurrency.chapter13;

public class ThreadCloseable extends Thread{
    private volatile boolean started = true;

    @Override
    public void run() {
        while (started){
            
        }
    }

    public void shutdown(){
        this.started = false;
    }
}
