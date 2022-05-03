package com.example.demo.javaconcurrency.chapter07.forkJoin;

import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Seller;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.AbstractLoadDataProcessor;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.Context;
import org.springframework.stereotype.Service;

@Service
public class SellerService extends AbstractLoadDataProcessor {
    @Override
    public void load(Context context) {
        System.out.println("SellerService-Thread : " + Thread.currentThread());
        Seller seller = new Seller();
        seller.setTotalNum(100);
        seller.setSellerNum(10);
        context.setSeller(seller);
    }
}
