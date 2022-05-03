package com.example.demo.concurrentcore.monitor;

import com.google.common.util.concurrent.Monitor;

public class MonitorExample1 {
    private static Monitor monitor = new Monitor();

    private static int x = 0;

    private static final int MAX_VALUE = 10;

    private static final Monitor.Guard INC_WHEN_LESS_10 = new Monitor.Guard(monitor) {

        @Override
        public boolean isSatisfied() {
            return x < MAX_VALUE;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            monitor.enterWhen(INC_WHEN_LESS_10);
            try {
                x++;
                System.out.println(Thread.currentThread() + ": x value is: " + x);
            } finally {
                monitor.leave();
            }
        }
    }
}

