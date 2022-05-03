package com.example.demo.concurrentcore.collectors;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample8 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        Integer result = stream
//                .collect(Collectors.summingInt(p -> (int) p.getPrice()));        

//        Double result = stream
//                .collect(Collectors.summingDouble(p -> p.getPrice()));        

        Long result = stream
                .collect(Collectors.summingLong(p -> (long) p.getPrice()));

        System.out.println(result);
    }
}
