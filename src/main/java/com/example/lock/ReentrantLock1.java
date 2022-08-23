package com.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReentrantLock1 {

    private static int i = -1;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable runnable = () -> {
            String nameThread = Thread.currentThread().getName();
            i++;
            System.out.println(nameThread + " lendo incremento " + i);
        };

        for (int j = 0; j < 6; j++) {
            executor.execute(runnable);
        }

        executor.shutdown();

    }

}
