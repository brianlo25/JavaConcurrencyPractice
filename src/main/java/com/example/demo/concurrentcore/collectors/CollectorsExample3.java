package com.example.demo.concurrentcore.collectors;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample3 {
    public static void main(String[] args) {
        // 傳統方法
        List<Production> list = CollectionUtils.returnList();

        final Map<String, List<Production>> prodLevel = new HashMap<>();

        for (Production p : list) {
            String level = calculateLevel(p.getPrice());
            prodLevel.computeIfAbsent(level, key -> new ArrayList<>()).add(p);
        }

        System.out.println(new Gson().toJson(prodLevel));

        // stream方法
        Stream<Production> stream = CollectionUtils.returnStream();

        Map<Boolean, List<Production>> level = stream
                .collect(Collectors.partitioningBy(p -> p.getPrice() > 100));

        System.out.println(new Gson().toJson(level));
    }

    private static String calculateLevel(double price) {
        if (price > 0 && price < 100) {
            return "LOW";
        }

        if (price >= 100) {
            return "High";
        }

        throw new IllegalArgumentException("Illegal production price.");
    }
}
