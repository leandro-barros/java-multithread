package com.example.executors.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsSchedule {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

//        executor.schedule(new Task(), 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
//        executor.shutdown();
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);// Caso a tarefa demore mais de 1 segundo o schedule espera ela finalizar para executar outra.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(LocalDateTime.now());

            String nameThread = Thread.currentThread().getName();
            int number = new Random().nextInt(1000);
            System.out.println("(Nome Thread: " + nameThread + ", número Random: " + number + ")");
        }
    }

// **************** Schedule com  Callable ******************
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
//
//        ScheduledFuture<String> future = executor.schedule(new Task(), 2, TimeUnit.SECONDS);
//
//        System.out.println(future.get());
//
//        executor.shutdown();
//    }
//
//    public static class Task implements Callable<String> {
//        @Override
//        public String call() throws Exception {
//            String nameThread = Thread.currentThread().getName();
//            int number = new Random().nextInt(1000);
//            return "(Nome Thread: " + nameThread + ", número Random: " + number + ")";
//        }
//    }
}
