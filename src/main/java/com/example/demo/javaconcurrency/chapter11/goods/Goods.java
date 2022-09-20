package com.example.demo.javaconcurrency.chapter11.goods;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Data
public class Goods {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer repo;
    private Integer buyerNum;
    private List<String> comment;

    public Goods(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
