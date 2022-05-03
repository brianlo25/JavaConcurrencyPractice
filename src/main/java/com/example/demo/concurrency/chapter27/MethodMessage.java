package com.example.demo.concurrency.chapter27;

import java.util.Map;

public abstract class MethodMessage {
    protected final Map<String, Object> params;

    protected final OrderService orderService;

    protected MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();
}
