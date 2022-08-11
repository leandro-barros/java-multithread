package com.example.syncrhronized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollections {

    // Não use List ou Map para multhread
    private static List<String> listThread = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // listThread = Collections.synchronizedList(listThread); forma de a lista sicronizar.

        MyRunnable myRunnable = new MyRunnable();

        Thread thread0 = new Thread(myRunnable);
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);

        thread0.start();
        thread1.start();
        thread2.start();

        Thread.sleep(500);
        System.out.println(listThread);

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            listThread.add("Add value in list");
        }
    }
}
