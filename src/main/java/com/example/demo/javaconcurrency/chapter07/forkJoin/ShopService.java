package com.example.demo.javaconcurrency.chapter07.forkJoin;

import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Shop;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.AbstractLoadDataProcessor;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.Context;
import org.springframework.stereotype.Service;

@Service
public class ShopService extends AbstractLoadDataProcessor {
    @Override
    public void load(Context context) {
        System.out.println("ShopService-Thread : " + Thread.currentThread());
        Shop shop = new Shop();
        shop.setName("咕泡小店");
        context.setShop(shop);
    }
}
