package com.example.demo.concurrentcore.collectors;

import java.util.stream.Stream;

public class CollectorsExample1 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        double totalPrice = stream
//                .filter(p -> p.getName().equals("cloth"))
//                .collect(Collectors.summarizingDouble(Production::getPrice))
//                .getSum();
//
//        System.out.println(totalPrice == 99.99d + 199.99d);

//        double totalPrice = stream
//                .filter(p -> p.getName().equals("cloth"))
//                .mapToDouble(Production::getPrice)
//                .sum();
//
//        System.out.println(totalPrice == 99.99d + 199.99d);

        double totalPrice = stream
                .filter(p -> p.getName().equals("cloth"))
                .mapToDouble(Production::getPrice)
                .reduce(0 , Double::sum);

        System.out.println(totalPrice == 99.99d + 199.99d);
    }
}
