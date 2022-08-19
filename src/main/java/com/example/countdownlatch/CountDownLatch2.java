package com.example.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatch2 {

    private static volatile int i = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        Runnable runnable = () -> {
            int j = new Random().nextInt(1000);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
            countDownLatch.countDown();
        };

        Runnable r2 = () -> {
            await();
            i = new Random().nextInt(100);
        };
        Runnable r3 = () -> {
            await();
            countDownLatch = new CountDownLatch(3);
        };
        Runnable r4 = () -> {
            await();
            System.out.println("Terminou. Vamos começar de novo?");
        };

        executor.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(r2, 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(r3, 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(r4, 0, 1, TimeUnit.SECONDS);
    }

    private static void await() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
