package concurrentcore.collectors;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample1 {
    public static void main(String[] args) {
        Stream<Production> stream = Stream.of(
                new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d)
        );

//        DoubleSummaryStatistics totalPrice = stream.filter(p -> p.getName().equals("cloth"))
//                .collect(Collectors.summarizingDouble(Production::getPrice));
//
//        System.out.println(totalPrice.getSum() == 99.99d + 199.99d);

//        double totalPrice = stream.filter(p -> p.getName().equals("cloth"))
//                .mapToDouble(Production::getPrice)
//                .sum();
//
//        System.out.println(totalPrice == 99.99d + 199.99d);

        double totalPrice = stream.filter(p -> p.getName().equals("cloth"))
                .mapToDouble(Production::getPrice)
                        .reduce(0 , Double::sum);

        System.out.println(totalPrice == 99.99d + 199.99d);
    }
}
