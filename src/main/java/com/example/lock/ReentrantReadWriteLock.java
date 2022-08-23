package com.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantReadWriteLock {

    private static int i = -1;

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable runnable = () -> {
            lock.lock();
            String nameThread = Thread.currentThread().getName();
            i++;
            System.out.println(nameThread + " lendo incremento " + i);
            lock.unlock();
        };

        for (int j = 0; j < 6; j++) {
            executor.execute(runnable);
        }

        executor.shutdown();

    }

}
