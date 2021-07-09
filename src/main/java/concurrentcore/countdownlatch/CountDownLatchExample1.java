package concurrentcore.countdownlatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountDownLatchExample1 {

    private static int[] getProductByCategoryId(){
        return IntStream.rangeClosed(1, 10).toArray();
    }

    private static class ProductPrice{
        private final int prodId;
        private double price;

        public ProductPrice(int prodId) {
            this(prodId, -1);
        }

        private ProductPrice(int prodId, double price) {
            this.prodId = prodId;
            this.price = price;
        }

        public int getProdId() {
            return prodId;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "ProductPrice{" +
                    "prodId=" + prodId +
                    ", price=" + price +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int[] products = getProductByCategoryId();

        List<ProductPrice> list = Arrays.stream(products).mapToObj(ProductPrice::new).collect(Collectors.toList());

        final CountDownLatch latch = new CountDownLatch(products.length);

        list.forEach(pp ->
                new Thread(() -> {
                    System.out.println(pp.getProdId() + " -> start calculate price.");
                    try {
                        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        if (pp.getProdId() % 2 == 0){
                            pp.setPrice(pp.prodId * 0.9D);
                        } else {
                            pp.setPrice(pp.prodId * 0.71D);
                        }
                        System.out.println(pp.getProdId() + " -> price calculate completed.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }).start()
        );

        latch.await();
        System.out.println("all of prices calculate finished.");
        list.forEach(System.out::println);
    }
}
