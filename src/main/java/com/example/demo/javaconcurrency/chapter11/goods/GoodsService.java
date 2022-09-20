package com.example.demo.javaconcurrency.chapter11.goods;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsService {
    public List<Goods> queryGoods() {
        return Arrays.asList(
                new Goods(1, "電腦", new BigDecimal(5000)),
                new Goods(2, "手機", new BigDecimal(3000)),
                new Goods(3, "書", new BigDecimal(99)),
                new Goods(4, "杯子", new BigDecimal(18))
        );
    }
}
