package com.example.demo.concurrency.chapter27;

import com.example.demo.concurrency.chapter19.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {
    @Override
    public void finish(T result) {
        super.finish(result);
    }
}
