package concurrentcore.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class MySpliterator<T> implements Spliterator<T> {
    private final T[] elements;
    private int currentIndex = 0;
    private final int CAPACITY;

    public MySpliterator(T[] elements) {
        this.elements = elements;
        this.CAPACITY = elements.length;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        action.accept(elements[currentIndex++]);
        return currentIndex < CAPACITY;
    }

    @Override
    public Spliterator<T> trySplit() {
        int remainSize = CAPACITY - currentIndex;
        if (remainSize < 10) {
            return null;
        }
        int middleSize = (remainSize) / 2;
        T[] newElements = (T[]) new Object[middleSize];
        System.arraycopy(elements, currentIndex, newElements, 0 ,middleSize);
        final MySpliterator<T> spliterator = new MySpliterator<T>(newElements);
        this.currentIndex = currentIndex + middleSize;
        return spliterator;
    }

    @Override
    public long estimateSize() {
        return CAPACITY - currentIndex;
    }

    @Override
    public int characteristics() {
        return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.NONNULL | Spliterator.IMMUTABLE;
    }

    public static void main(String[] args) {
        Integer[] ints = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

        MySpliterator<Integer> mySpliterator = new MySpliterator<>(ints);
        Spliterator<Integer> s1 = mySpliterator.trySplit();
        Spliterator<Integer> s2 = mySpliterator.trySplit();

        s1.forEachRemaining(System.out::println);
        System.out.println("===============");

        s2.forEachRemaining(System.out::println);
        System.out.println("===============");

        mySpliterator.forEachRemaining(System.out::println);
    }
}
