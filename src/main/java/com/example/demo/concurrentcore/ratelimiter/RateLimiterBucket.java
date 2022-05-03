package com.example.demo.concurrentcore.ratelimiter;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class RateLimiterBucket {
    static class Request {
        private final int data;

        Request(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "data=" + data +
                    '}';
        }
    }

    private final ConcurrentLinkedQueue<Request> bucket = new ConcurrentLinkedQueue<>();

    private final static int BUCKET_CAPACITY = 1000;

    private final RateLimiter rateLimiter = RateLimiter.create(10.0D);

    private final Monitor requestMonitor = new Monitor();

    private final Monitor handleMonitor = new Monitor();

    public void submitRequest(int data) {
        this.submitRequest(new Request(data));
    }

    public void submitRequest(Request request) {
        if (requestMonitor.enterIf(requestMonitor.newGuard(() -> bucket.size() < BUCKET_CAPACITY))) {
            try {
                boolean result = bucket.offer(request);
                if (result) {
                    System.out.println(Thread.currentThread() + " submit request: " + request.getData() + " successfully.");
                } else {
                    // produce into MQ and will try again later.
                }
            } finally {
                requestMonitor.leave();
            }
        } else {
            System.out.println("The request:" + request.getData() + " will be down-dimensional handle due to bucket is overflow.");
            // produce into MQ and will try again later.
        }
    }

    public void handleRequest(Consumer<Request> consumer) {
        if (handleMonitor.enterIf(handleMonitor.newGuard(() -> !bucket.isEmpty()))) {
            try {
                rateLimiter.acquire();
                consumer.accept(bucket.poll());
            } finally {
                handleMonitor.leave();
            }
        }
    }
}
