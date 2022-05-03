package com.example.demo.concurrency.chapter09;

import java.util.Random;

public class GlobalConstants {
    static {
        System.out.println("The GlobalConstants will be initialized");
    }

    public final static int MAX = 100;

    public final static  int RANDOM = new Random().nextInt();
}
