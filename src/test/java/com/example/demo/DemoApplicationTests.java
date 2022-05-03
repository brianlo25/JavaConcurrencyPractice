package com.example.demo;

import com.example.demo.javaconcurrency.chapter06.countDownLatch.ApplicationStartUp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testCountDownLatch() {
        boolean result = true;
        try {
            result = ApplicationStartUp.checkExternalService();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有服務都已經啟動成功:" + result);
    }

}
