package com.example.demo.concurrentcore.cyclicbarrier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CyclicBarrierExample1 {

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

    public static void main(String[] args) {
        final int[] products = getProductByCategoryId();

        List<ProductPrice> list = Arrays.stream(products).mapToObj(ProductPrice::new).collect(Collectors.toList());

        final CyclicBarrier barrier = new CyclicBarrier(list.size());

        final List<Thread> threadList = new ArrayList<>();

        list.forEach(pp -> {
            Thread thread = new Thread(() -> {
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
                    try {
                        barrier.await();
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadList.add(thread);
            thread.start();
        });

        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("all of prices calculate finished.");
        list.forEach(System.out::println);
    }
}
