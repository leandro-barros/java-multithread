package com.example.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semaphore2 {

    private static final Semaphore SEMAPHORE = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            int idUser = new Random().nextInt(10000);

            boolean ok = false;
            while (!ok) {
                ok = tryAcquire();
            }
            System.out.println("Print nome usuário (" + idUser + ") com thread " + name);
            sleep();
            SEMAPHORE.release();
        };


        for (int i = 0; i < 500; i++) {
            executor.execute(r1);
        }

        executor.shutdown();
    }

    private static boolean tryAcquire() {
        try {
            return SEMAPHORE.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return false;
        }
    }

    private static void sleep() {
        try {
            int time = new Random().nextInt(6);
            time++;
            Thread.sleep(1000 * time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
