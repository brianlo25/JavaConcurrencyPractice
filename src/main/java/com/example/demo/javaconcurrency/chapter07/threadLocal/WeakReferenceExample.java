package com.example.demo.javaconcurrency.chapter07.threadLocal;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    static Object object = new Object();

    public static void main(String[] args) {
        WeakReference<Object> objectWeakReference = new WeakReference<>(object);
        object = null;
        System.gc();
        System.out.println("gc之後:" + objectWeakReference.get());
    }
}
