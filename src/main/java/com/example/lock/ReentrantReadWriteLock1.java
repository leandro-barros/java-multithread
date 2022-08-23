package com.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLock1 {

    private static int i = -1;

    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable runnable = () -> {
            Lock writeLock = lock.writeLock();
            writeLock.lock();
            String nameThread = Thread.currentThread().getName();
            i++;
            System.out.println(nameThread + " lendo incremento " + i);
            writeLock.unlock();
        };

        for (int j = 0; j < 6; j++) {
            executor.execute(runnable);
        }

        executor.shutdown();

    }

}
