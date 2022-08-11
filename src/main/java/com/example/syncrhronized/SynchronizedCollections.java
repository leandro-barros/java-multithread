package com.example.syncrhronized;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedCollections {

    private static List<String> listThread = new ArrayList<>();

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

        }
    }
}
