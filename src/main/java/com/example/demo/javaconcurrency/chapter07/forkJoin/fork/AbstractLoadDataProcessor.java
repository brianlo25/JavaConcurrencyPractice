package com.example.demo.javaconcurrency.chapter07.forkJoin.fork;

import com.example.demo.javaconcurrency.chapter07.forkJoin.ILoadDataProcessor;

import java.util.concurrent.RecursiveAction;

public abstract class AbstractLoadDataProcessor extends RecursiveAction implements ILoadDataProcessor {
    protected Context context;

    @Override
    protected void compute() {
        load(context);
    }

    public Context getContext() {
        this.join();
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
