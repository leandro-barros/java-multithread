package com.example.executors.multhread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsMultithread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = null;
        try {
//            -------------------------------------- Primeiro exemplo
//            executor = Executors.newFixedThreadPool(4);
//            executor = Executors.newCachedThreadPool(); // Cria thread até quando precisar no que pode ser um problema
//            Future<String> future1 = executor.submit(new Task());
//            System.out.println(future1.get());
//            Future<String> future2 = executor.submit(new Task());
//            Future<String> future3 = executor.submit(new Task());
//            System.out.println(future2.get());
//            System.out.println(future3.get());
//            executor.shutdown();

//            -------------------------------------- Segundo exemplo
//            executor = Executors.newCachedThreadPool();
//
//            List<Task> tasks = new ArrayList<>();
//            for (int i = 0; i < 100; i++) {
//                tasks.add(new Task());
//            }
//
//            List<Future<String>> futures = executor.invokeAll(tasks);
//
//            for (Future<String> future : futures) {
//                System.out.println(future.get());
//            }
//            executor.shutdown();

//            -------------------------------------- Terceiro exemplo

            executor = Executors.newCachedThreadPool();

            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                tasks.add(new Task());
            }

            List<Future<String>> futures = executor.invokeAll(tasks);

            for (Future<String> future : futures) {
                System.out.println(future.get());
            }

            executor.shutdown();
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }

    public static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            String nameThread = Thread.currentThread().getName();
            int number = new Random().nextInt(1000);
            return "(Nome Thread: " + nameThread + ", número Random: " + number + ")";
        }
    }
}
