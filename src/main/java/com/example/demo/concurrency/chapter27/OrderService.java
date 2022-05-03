package com.example.demo.concurrency.chapter27;

import com.example.demo.concurrency.chapter19.Future;

public interface OrderService {

    Future<String> findOrderDetails(long orderId);

    void order(String account, long orderId);
}
