package com.example.demo.concurrentcore.jmh;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArrayListVsLinkedList {
    private final static String DATA = "DUMMY DATA";

    private final static int MAX_CAPACITY = 1000000;

    private final static int MAX_ITERATIONS = 10;

    private static void test(List<String> list){
        for (int i = 0; i < MAX_CAPACITY; i++){
            list.add(DATA);
        }
    }

    private static void arrayListPerfTest(int iteration){
        final List<String> list = new ArrayList<>();
        final Stopwatch stopwatch = Stopwatch.createStarted();
        test(list);
        System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    private static void linkedListPerfTest(int iteration){
        final List<String> list = new LinkedList<>();
        final Stopwatch stopwatch = Stopwatch.createStarted();
        test(list);
        System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    public static void main(String[] args) {
        arrayListPerfTest(MAX_ITERATIONS);
        System.out.println(Strings.repeat("#", 100));
        linkedListPerfTest(MAX_ITERATIONS);
    }
}
