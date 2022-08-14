package com.example.syncrhronized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CollectionsForConcurrency {

    // Coleções do tipo Thread-safe
//    private static List<String> listThread = new CopyOnWriteArrayList<>();
//    private static Map<Integer, String> map = new ConcurrentHashMap<>();
        private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

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
//        System.out.println(map);
        System.out.println(queue);

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
//            listThread.add("Add value in list");
//            map.put(new Random().nextInt(), "Add value in map");
            queue.add("Se tiver condições, apoia");
            String name = Thread.currentThread().getName();
            System.out.println(name + " inseriu na lista");
        }
    }
}
