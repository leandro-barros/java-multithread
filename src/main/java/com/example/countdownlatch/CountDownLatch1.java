package com.example.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatch1 {

    private static volatile int i = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        Runnable runnable = () -> {
            int j = new Random().nextInt(1000);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
            countDownLatch.countDown();
        };

        executor.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);

        while (true) {
            await();
            i = new Random().nextInt(100);
            countDownLatch = new CountDownLatch(3);
        }

//        while (true) {
//            sleep();
//            i = new Random().nextInt(100);
//        }
    }

    private static void await() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    private static void sleep() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
