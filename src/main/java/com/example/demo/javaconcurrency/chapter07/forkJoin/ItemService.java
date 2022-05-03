package com.example.demo.javaconcurrency.chapter07.forkJoin;

import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Item;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.AbstractLoadDataProcessor;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.Context;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractLoadDataProcessor {
    @Override
    public void load(Context context) {
        System.out.println("ItemService-Thread : " + Thread.currentThread());
        Item item = new Item();
        item.setNum(100);
        item.setProductName("鍵盤");
        context.setItem(item);
    }
}
