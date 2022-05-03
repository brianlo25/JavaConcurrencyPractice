package com.example.demo.concurrentcore.collectors;

import com.google.gson.Gson;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample12 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        Map<Boolean, List<Production>> result = stream
//                .collect(Collectors.partitioningBy(p -> p.getPrice() > 100));

//        Map<Boolean, Set<Production>> result = stream
//                .collect(Collectors.partitioningBy(p -> p.getPrice() > 100, Collectors.toSet()));

//        Map<Boolean, Double> result = stream
//                .collect(Collectors.partitioningBy(p -> p.getPrice() > 100, Collectors.summingDouble(Production::getPrice)));

        Map<Boolean, Double> result = stream
                .collect(Collectors.partitioningBy(p -> p.getPrice() > 100, Collectors.averagingDouble(Production::getPrice)));

        System.out.println(new Gson().toJson(result));
    }
}
