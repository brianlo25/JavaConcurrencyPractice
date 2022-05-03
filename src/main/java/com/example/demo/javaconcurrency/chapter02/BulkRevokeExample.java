package com.example.demo.javaconcurrency.chapter02;

import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BulkRevokeExample {
    public static void main(String[] args) throws InterruptedException, IOException {
        List<BulkRevokeExample> bulks = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                BulkRevokeExample a = new BulkRevokeExample();
                synchronized (a) {
                    bulks.add(a);
                }
            }
        });
        t1.start();
        t1.join();
        System.out.println("打印t1線程，bulks中第20個對象的對象頭");
        System.out.println(ClassLayout.parseInstance(bulks.get(19)).toPrintable());
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                BulkRevokeExample a = bulks.get(i);
                synchronized (a) {
                    if (i == 18 || i == 19) {
                        System.out.println("第" + (i + 1) + "次偏向結果");
                        System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    }
                }
            }
        });
        t2.start();
        System.in.read();
    }
}
