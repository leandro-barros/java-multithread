package com.example;

public class Threads_1 {

    public static void main(String[] args) {
        // Thread atual
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());

        Thread threadRunnable = new Thread(new MyRunnable());
        threadRunnable.run();

    }


}
