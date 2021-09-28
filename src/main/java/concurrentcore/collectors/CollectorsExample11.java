package concurrentcore.collectors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample11 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        Set<String> set = stream
//                .map(Production::getName)
//                .collect(Collectors.toSet());
//
//        System.out.println(set);

//        List<String> list = stream
//                .map(Production::getName)
//                .collect(Collectors.toList());
//
//        System.out.println(list);

//        Stream<String[]> newStream = Stream
//                .of(new String[][]{{"Java", "Java Programming"}, {"C", "C Programming"}, {"Scala", "Scala Programming"}});
//
//        Map<String, String> result = newStream
//                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
//
//        System.out.println(result);

//        Map<String, List<Production>> result = stream
//                .collect(Collectors.toMap(Production::getName, Arrays::asList, (productions, productions2) -> {
//            List<Production> mergeResult = new ArrayList<>();
//            mergeResult.addAll(productions);
//            mergeResult.addAll(productions2);
//            return mergeResult;
//        }));

        Map<String, List<Production>> result = stream
                .collect(Collectors.toMap(Production::getName, Arrays::asList, (productions, productions2) -> {
                    List<Production> mergeResult = new ArrayList<>();
                    mergeResult.addAll(productions);
                    mergeResult.addAll(productions2);
                    return mergeResult;
                }, TreeMap::new));

        System.out.println(result);
    }
}
