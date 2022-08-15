package com.example.atomicclass;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicClass {

//    static AtomicInteger i = new AtomicInteger(-1);
//    static AtomicBoolean b = new AtomicBoolean(false);
    static AtomicReference<Object> r = new AtomicReference<>(new Object());

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread0 = new Thread(myRunnable);
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);

        thread0.start();
        thread1.start();
        thread2.start();

    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            String nameThread = Thread.currentThread().getName();
//            System.out.println(nameThread + ": " + i.incrementAndGet());
//            System.out.println(nameThread + ": " + b.compareAndExchange(false, true));
            System.out.println(nameThread + ": " + r.getAndSet(new String()));
        }
    }

}
