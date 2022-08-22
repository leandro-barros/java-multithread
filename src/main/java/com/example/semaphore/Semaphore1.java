package com.example.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Semaphore1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            int idUser = new Random().nextInt(10000);
            System.out.println("Print nome usuário (" + idUser + ") com thread " + name);
        };

        for (int i = 0; i < 500; i++) {
            executor.execute(r1);
        }

        executor.shutdown();
    }
}
