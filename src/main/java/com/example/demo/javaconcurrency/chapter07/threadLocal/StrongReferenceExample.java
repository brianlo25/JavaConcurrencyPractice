package com.example.demo.javaconcurrency.chapter07.threadLocal;

public class StrongReferenceExample {
    static Object object = new Object();

    public static void main(String[] args) {
        Object strongRef = object;
        object = null;
        System.gc();
        System.out.println("gc之後:" + strongRef);
    }
}
