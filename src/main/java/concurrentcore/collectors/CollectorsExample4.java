package concurrentcore.collectors;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample4 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

        Double averagePrice = stream
                .collect(Collectors.averagingDouble(Production::getPrice));

//        Double averagePrice = stream.collect(Collectors.averagingInt(p -> (int) p.getPrice()));

//        Double averagePrice = stream.collect(Collectors.averagingLong(p -> (long) p.getPrice()));

        System.out.println(averagePrice);
    }
}
