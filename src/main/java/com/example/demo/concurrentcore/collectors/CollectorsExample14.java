package com.example.demo.concurrentcore.collectors;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample14 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        Optional<Production> opt = stream
//                .collect(Collectors.reducing((p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2));
//
//        opt.ifPresent(System.out::println);

//        Production book = stream
//                .collect(Collectors.reducing(new Production("Book", 279.9), (p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2));
//
//        System.out.println(new Gson().toJson(book));

        Comparator<Double> comparing = Comparator.comparing(Double::doubleValue);

        Double highestPrice = stream
                .collect(Collectors.reducing(0.0D, Production::getPrice, BinaryOperator.maxBy(comparing)));

        System.out.println(highestPrice);

    }
}
