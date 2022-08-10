package com.example.syncrhronized;

public class Syncrhronized_1 {

    static int i = -1;

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread0 = new Thread(myRunnable);
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        Thread thread3 = new Thread(myRunnable);
        Thread thread4 = new Thread(myRunnable);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

    public static void prints() {
        synchronized (Syncrhronized_1.class) {
            i++;
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + ": " + i);
        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        // public synchronized void run() {
        public void run() {
           prints();

            /* i++;
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + ": " + i); */

            /* synchronized (this) { Outra forma de tratar concorrência de threads.
                i++;
                String nameThread = Thread.currentThread().getName();
                System.out.println(nameThread + ": " + i);
            } */
        }
    }
}
