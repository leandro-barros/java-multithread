package com.example.cyclicbarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable r1 = () -> {
            System.out.println(432d * 3d);
        };
        Runnable r2 = () -> {
            System.out.println(Math.pow(3d, 14d));
        };
        Runnable r3 = () -> {
            System.out.println(45d * 127d / 12d);
        };

        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);

        executor.shutdown();
    }

}
