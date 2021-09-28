package concurrentcore.collectors;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample7 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        String result = stream
//                .collect(Collectors.mapping(p -> p.getName(), Collectors.joining()));

//        String result = stream
//                .collect(Collectors.mapping(p -> p.getName(), Collectors.joining("#")));

        String result = stream
                .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(",", "(", ")")));

        System.out.println(result);
    }
}
