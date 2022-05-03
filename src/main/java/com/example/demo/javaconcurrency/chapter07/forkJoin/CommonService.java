package com.example.demo.javaconcurrency.chapter07.forkJoin;

import com.example.demo.javaconcurrency.chapter07.forkJoin.domain.Comment;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.AbstractLoadDataProcessor;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.Context;
import org.springframework.stereotype.Service;

@Service
public class CommonService extends AbstractLoadDataProcessor {
    @Override
    public void load(Context context) {
        System.out.println("CommonService-Thread : " + Thread.currentThread());
        Comment comment = new Comment();
        comment.setContent("商品質量很好");
        comment.setName("Mic");
        context.setComment(comment);
    }
}
