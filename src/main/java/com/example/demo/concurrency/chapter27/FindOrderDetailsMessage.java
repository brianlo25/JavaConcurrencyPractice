package com.example.demo.concurrency.chapter27;

import com.example.demo.concurrency.chapter19.Future;

import java.util.Map;

public class FindOrderDetailsMessage extends MethodMessage{
    protected FindOrderDetailsMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        Future<String> realFuture = orderService.findOrderDetails((Long) params.get("orderId"));
        ActiveFuture<String> activeFuture = (ActiveFuture<String>)params.get("activeFuture");
        try {
            String result = realFuture.get();
            activeFuture.finish(result);
        } catch (InterruptedException e) {
           activeFuture.finish(null);
        }
    }
}
