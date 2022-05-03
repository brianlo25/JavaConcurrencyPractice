package com.example.demo.designpattern.chapter04;

import java.util.concurrent.Callable;

public interface Blocker {
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws Exception;

    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
