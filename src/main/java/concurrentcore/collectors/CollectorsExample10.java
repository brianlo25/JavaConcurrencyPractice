package concurrentcore.collectors;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample10 {
    public static void main(String[] args) {
        Stream<Production> stream = CollectionUtils.returnStream();

//        IntSummaryStatistics stat = stream
//                .collect(Collectors.summarizingInt(p -> (int) p.getPrice()));        

//        DoubleSummaryStatistics stat = stream
//                .collect(Collectors.summarizingDouble(Production::getPrice));

        LongSummaryStatistics stat = stream
                .collect(Collectors.summarizingLong(p -> (long) p.getPrice()));

        System.out.println(stat);
    }
}
