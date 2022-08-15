package com.example.volatilethread;

public class Volatile {

    private static int number = 0;
    private static boolean prepared = false;

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (!prepared) {
                Thread.yield();
            }

            System.out.println(number);
        }
    }
}
