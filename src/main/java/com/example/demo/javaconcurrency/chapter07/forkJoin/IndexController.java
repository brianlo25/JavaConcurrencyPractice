package com.example.demo.javaconcurrency.chapter07.forkJoin;

import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ForkJoinPool;

@RestController
public class IndexController {
    @Autowired
    ItemTaskForkJoinDataProcessor itemTaskForkJoinDataProcessor;

    ItemTaskForkJoinDataProcessor subTask;

    @GetMapping("/say")
    public Context index() {
        Context context = new Context();
        itemTaskForkJoinDataProcessor.setContext(context);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(itemTaskForkJoinDataProcessor);
        return itemTaskForkJoinDataProcessor.getContext();
    }
}
