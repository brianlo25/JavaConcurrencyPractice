package concurrentcore.collectors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample2 {
    public static void main(String[] args) {
        // 傳統方法
        List<Production> list = Arrays.asList(
                new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d)
        );

        final Map<String, Double> prodPrice = new HashMap<>();

        for (Production p : list) {
            String prodName = p.getName();
            double price = p.getPrice();
            if (prodPrice.containsKey(prodName)){
                Double totalPrice = prodPrice.get(prodName);
                prodPrice.put(prodName, totalPrice + price);
            } else {
                prodPrice.put(prodName, price);
            }
        }

        System.out.println(prodPrice.size() == 4);
        System.out.println(prodPrice.get("T-Shirt") == 43.34d);
        System.out.println(prodPrice.get("cloth") == 99.99d + 199.99d);
        System.out.println(prodPrice.get("shoe") == 123.8d + 32.5d);
        System.out.println(prodPrice.get("hat") == 26.5d);

        // stream方法
        Stream<Production> stream = Stream.of(
                new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d)
        );

        Map<String, DoubleSummaryStatistics> groupingPrice = stream.collect(Collectors.groupingBy(
                Production::getName,
                Collectors.summarizingDouble(Production::getPrice)));

        System.out.println(groupingPrice.size() == 4);
        System.out.println(groupingPrice.get("T-Shirt").getSum() == 43.34d);
        System.out.println(groupingPrice.get("cloth").getSum() == 99.99d + 199.99d);
        System.out.println(groupingPrice.get("shoe").getSum() == 123.8d + 32.5d);
        System.out.println(groupingPrice.get("hat").getSum() == 26.5d);

    }
}
