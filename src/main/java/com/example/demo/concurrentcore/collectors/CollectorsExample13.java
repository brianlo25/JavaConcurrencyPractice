package com.example.demo.concurrentcore.collectors;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample13 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        Map<String, List<Production>> result = stream
//                .collect(Collectors.groupingBy(Production::getName));

//        Map<String, Set<Production>> result = stream
//                .collect(Collectors.groupingBy(Production::getName, Collectors.toSet()));

        Map<String, Set<Production>> result = stream
                .collect(Collectors.groupingBy(Production::getName, TreeMap::new, Collectors.toSet()));

        System.out.println(new Gson().toJson(result));
    }
}
