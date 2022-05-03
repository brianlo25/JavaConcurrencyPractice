package com.example.demo.concurrency.chapter10;

public class NameSpace {
    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader classLoader = NameSpace.class.getClassLoader();
        MyClassLoader classLoader1 = new MyClassLoader("C:\\classLoader1", null);
        MyClassLoader classLoader2 = new MyClassLoader("C:\\classLoader1", null);
//        BrokerDelegateClassLoader classLoader2 = new BrokerDelegateClassLoader("C:\\classLoader1", null);
        Class<?> aClass = classLoader1.loadClass("com.example.demo.concurrency.chapter10.Test");
        Class<?> bClass = classLoader2.loadClass("com.example.demo.concurrency.chapter10.Test");
        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);
    }
}
