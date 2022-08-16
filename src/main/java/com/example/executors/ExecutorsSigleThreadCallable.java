package com.example.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorsSigleThreadCallable {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Task()); // Pode-se ser chamado várias vezes
            executorService.execute(new Task());
            Future<?> future = executorService.submit(new Task());

            System.out.println(future.isDone());
            executorService.shutdown();// Finaliza todas as tarefas e não aceita novas.
            executorService.awaitTermination(10, TimeUnit.SECONDS); // Usa com  executorService.shutdown(); este espera a tarefa finalizar
            System.out.println(future.isDone());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (executorService != null) {
//                executorService.shutdown();
                executorService.shutdownNow(); // Finaliza tarefas um pouco mais agrassiva
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
