package concurrentcore.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CollectionUtils {
    public static final Stream<Production> returnStream() {
        return Stream.of(
                new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d)
        );
    }

    public static final List<Production> returnList() {
        return Arrays.asList(
                new Production("T-Shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d)
        );
    }
}
