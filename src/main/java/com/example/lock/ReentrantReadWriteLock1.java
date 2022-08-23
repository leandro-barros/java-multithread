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

        Runnable r1 = () -> {
            Lock writeLock = lock.writeLock();
            writeLock.lock();
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + " - Escrevendo: " + i);
            i++;
            System.out.println(nameThread + " - Escrito: " + i);
            writeLock.unlock();
        };

        Runnable r2 = () -> {
            Lock readLock = lock.readLock();
            readLock.lock();
            System.out.println("Lendo: " + i);
            System.out.println("Lido: " + i);
            readLock.unlock();
        };

        for (int j = 0; j < 6; j++) {
            executor.execute(r1);
            executor.execute(r2);
        }

        executor.shutdown();

    }

}
