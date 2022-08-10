package com.example.syncrhronized;

public class Syncrhronized {

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

    public static class MyRunnable implements Runnable {

        @Override
        // public void run() {
        public synchronized void run() {
            i++;
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + ": " + i);
        }
    }
}
