package com.example.semaphore;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore2 {

    private static final Semaphore SEMAPHORE = new Semaphore(3);

    private static final AtomicInteger QUANTY = new AtomicInteger(0);

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(501);

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            int idUser = new Random().nextInt(10000);

            boolean ok = false;

            QUANTY.incrementAndGet();
            while (!ok) {
                ok = tryAcquire(); // Tenta conseguir vaga para thread
            }
            QUANTY.decrementAndGet();

            System.out.println("Print nome usuário (" + idUser + ") com thread " + name);
            sleep();
            SEMAPHORE.release(); // Libera thread
        };

        Runnable r2 = () -> {
            System.out.println(QUANTY.get());
        };

        for (int i = 0; i < 500; i++) {
            executor.execute(r1);
        }

        executor.scheduleWithFixedDelay(r2, 0, 100, TimeUnit.MILLISECONDS);

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
