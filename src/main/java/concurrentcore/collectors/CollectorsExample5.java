package concurrentcore.collectors;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample5 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

        Double averagePriceVND = stream
                .collect(Collectors.collectingAndThen(Collectors.averagingDouble(Production::getPrice), p -> p * 3264.491d));

        System.out.println(averagePriceVND);
    }
}
