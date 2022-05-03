package com.example.demo.designpattern.chapter05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTerminatableThread extends Thread implements Terminatable{
    final static Logger logger = LoggerFactory.getLogger(AbstractTerminatableThread.class);
    private final boolean DEBUG = true;
    public final TerminationToken terminationToken;

    public AbstractTerminatableThread() {
        this(new TerminationToken());
    }

    public AbstractTerminatableThread(TerminationToken terminationToken){
        this.terminationToken = terminationToken;
        terminationToken.register(this);
    }

    protected abstract void doRun() throws Exception;

    protected void doCleanUp(Exception cause){

    }

    protected void doTerminate(){

    }

    @Override
    public void run() {
        Exception ex = null;
        try {
            for (;;){
                if (terminationToken.isToShutDown() && terminationToken.reservations.get() <= 0){
                    break;
                }
                doRun();
            }
        } catch (Exception e) {
            ex = e;
            if (e instanceof InterruptedException){
                if (DEBUG){
                    logger.debug(e.getMessage());
                }
            } else {
                logger.error("", e.getMessage());
            }
        } finally {
            try {
                doCleanUp(ex);
            } finally {
                terminationToken.notifyThreadTermination(this);
            }
        }
    }

    @Override
    public void interrupt() {
        terminate();
    }

    @Override
    public void terminate() {
        terminationToken.setToShutDown(true);
        try {
            doTerminate();
        }finally {
            if (terminationToken.reservations.get() <= 0){
                super.interrupt();
            }
        }
    }

    public void terminate(boolean waitUntilThreadTerminate){
        terminate();
        if (waitUntilThreadTerminate){
            try {
                this.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
