package com.example.demo.javaconcurrency.chapter07.forkJoin.fork;

import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Comment;
import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Item;
import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Seller;
import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Shop;
import lombok.Data;

@Data
public class Context {
    private Item item;
    private Comment comment;
    private Seller seller;
    private Shop shop;
}
