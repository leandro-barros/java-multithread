package com.example.executors;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsSigleThreadCallable {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            Future<String> future = executorService.submit(new Task());

            System.out.println("Finalizou: " + future.isDone());
            String value = future.get();
//            String value = future.get(1, TimeUnit.SECONDS);
            System.out.println("Value: " + value);
            System.out.println("Finalizou: " + future.isDone());

//            executorService.shutdown();// Finaliza todas as tarefas e não aceita novas.
//            executorService.awaitTermination(10, TimeUnit.SECONDS); // Usa com  executorService.shutdown(); este espera a tarefa finalizar
//            System.out.println(future.isDone());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (executorService != null) {
//                executorService.shutdown();
                executorService.shutdownNow(); // Finaliza tarefas um pouco mais agrassiva
            }
        }
    }

    private static class Task implements Callable<String> { // O callable retorna um valor.
        @Override
        public String call() throws Exception {
            String nameThread = Thread.currentThread().getName();
            int number = new Random().nextInt(1000);
            return "(Nome Thread: " + nameThread + ", número Random: " + number + ")";
        }
    }
}
