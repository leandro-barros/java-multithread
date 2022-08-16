package com.example.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsSigleThreadCallable {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Task());

            executorService.awaitTermination(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + ": Executou.");
        }
    }
}
