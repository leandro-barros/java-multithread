package com.example.executors.schedule;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsSchedule {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        ScheduledFuture<String> future = executor.schedule(new Task(), 2, TimeUnit.SECONDS);

        System.out.println(future.get());

        executor.shutdown();
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
