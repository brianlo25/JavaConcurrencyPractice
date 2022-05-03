package com.example.demo.concurrency.chapter27;

import com.example.demo.concurrency.chapter19.Future;

public class ActiveOrderServiceTest {
    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = ActiveServiceFactory.active(new OrderServiceImpl());
        Future<String> future = orderService.findOrderDetails(23423);
        System.out.println("I will be returned immediately");
        System.out.println(future.get());
    }
}
