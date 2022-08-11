package com.example.startthread;

public class Threads_1 {

    public static void main(String[] args) {
        // Thread atual
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());

        Thread threadRunnable = new Thread(new MyRunnable());
        // threadRunnable.run(); Apenas executand mesma thread.
        threadRunnable.start(); // Executa em uma nova thread.

        // Runnable with lambida
        Thread thread2 = new Thread(
                () -> System.out.println("Thread with lambida")
        );
        thread2.start();
        // thread1.start(); No make, throw exception

        // Thread 3
        Thread thread3 = new Thread(new MyRunnable());
        thread3.start();

    }


}
