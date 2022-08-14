package com.example.atomicclass;

public class AtomicClass {

    static int i = -1;

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
            i++;
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + ": " + i);
        }
    }

}
