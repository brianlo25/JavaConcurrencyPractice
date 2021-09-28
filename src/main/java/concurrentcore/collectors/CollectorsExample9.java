package concurrentcore.collectors;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample9 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        Optional<Production> opt = stream
//                .collect(Collectors.maxBy((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())));

        Optional<Production> opt = stream
                .collect(Collectors.minBy((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())));

        opt.ifPresent(p -> System.out.println(p.getName()));
    }
}
