package com.example.volatilethread;

public class Volatile_2 {

    // Usar volatile para processador não pegar valor do cache e sim ir na memória RAM.

//    private static int number = 0;
//    private static boolean prepared = false;

    private static volatile int number = 0;
    private static volatile boolean prepared = false;

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (!prepared) {
                Thread.yield();
            }

            if(number != 42) {
                throw new IllegalStateException("Ocorreu um erro.");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            Thread t0 = new Thread(new MyRunnable());
            t0.start();
            Thread t1 = new Thread(new MyRunnable());
            t1.start();
            Thread t2 = new Thread(new MyRunnable());
            t2.start();

            number = 42;
            prepared = true;

            while (
                    t0.getState() != Thread.State.TERMINATED
                    || t1.getState() != Thread.State.TERMINATED
                    || t2.getState() != Thread.State.TERMINATED
            ) {
                // espera
            }

            number = 0;
            prepared = false;
        }
    }
}
