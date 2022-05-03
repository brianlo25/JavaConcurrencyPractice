package com.example.demo.concurrency.chapter10;

public class LoadString {
    public static void main(String[] args) throws ClassNotFoundException {
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("C:\\classLoader3");
        Class<?> aClass = classLoader.loadClass("java.lang.String");
    }
}
