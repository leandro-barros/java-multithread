package com.example.syncrhronized;

public class Synchronized_2 {

    private static int i = 0;

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
        public void run() {
            int j;
            synchronized (this) {
                i++;
                j = i * 2;
            }

            double jElevated100 = Math.pow(j, 100);
            double sqrt = Math.sqrt(jElevated100);
            System.out.println(sqrt);
        }

    }
}
