package com.example.demo.javaconcurrency.chapter02;

import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutExample {
    public static void main(String[] args) {
        ClassLayoutExample example = new ClassLayoutExample();
        System.out.println(ClassLayout.parseInstance(example).toPrintable());
    }
}
