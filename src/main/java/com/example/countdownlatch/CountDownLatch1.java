package com.example.countdownlatch;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatch1 {

    private static int i = 0;

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        Runnable runnable = () -> {
            int j = new Random().nextInt(1000);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
        };

        executor.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }
}
