package com.example.syncrhronized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsForConcurrency {

    // Coleções do tipo Thread-safe
//    private static List<String> listThread = new CopyOnWriteArrayList<>();
    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread0 = new Thread(myRunnable);
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);

        thread0.start();
        thread1.start();
        thread2.start();

        Thread.sleep(500);
//        System.out.println(listThread);
        System.out.println(map);

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
//            listThread.add("Add value in list");
            map.put(new Random().nextInt(), "Add value in list");
            String name = Thread.currentThread().getName();
            System.out.println(name + " inseriu na lista");
        }
    }
}
