package concurrentcore.collectors;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample6 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

        double deductIncome = stream
                .collect(Collectors.mapping(p -> p.getPrice() * 0.1, Collectors.summarizingDouble(Double::doubleValue)))
                .getSum();

        // 相同的結果
//        double deductIncome = stream
//                .map(p -> p.getPrice() * 0.1)
//                .mapToDouble(Double::doubleValue)
//                .sum();

        System.out.println(deductIncome);
    }
}
