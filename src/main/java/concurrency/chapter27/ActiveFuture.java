package concurrency.chapter27;

import concurrency.chapter19.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {
    @Override
    public void finish(T result) {
        super.finish(result);
    }
}
