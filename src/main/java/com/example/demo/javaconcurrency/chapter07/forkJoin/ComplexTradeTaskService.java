package com.example.demo.javaconcurrency.chapter07.forkJoin;

import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.AbstractLoadDataProcessor;
import com.example.demo.javaconcurrency.chapter07.forkJoin.fork.Context;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
@Service
public class ComplexTradeTaskService extends AbstractLoadDataProcessor implements ApplicationContextAware {
    ApplicationContext applicationContext;
    private List<AbstractLoadDataProcessor> taskDataProcessorList = new ArrayList<>();
    @Override
    public void load(Context context) {
        this.taskDataProcessorList.forEach(abstractLoadDataProcessor -> {
            abstractLoadDataProcessor.setContext(this.context);
            abstractLoadDataProcessor.fork();
        });
    }

    @Override
    public Context getContext() {
        this.taskDataProcessorList.forEach(ForkJoinTask::join);
        return super.getContext();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        taskDataProcessorList.add(applicationContext.getBean(SellerService.class));
        taskDataProcessorList.add(applicationContext.getBean(ShopService.class));

    }
}
