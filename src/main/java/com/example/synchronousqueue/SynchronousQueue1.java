package com.example.synchronousqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueue1 {

    private static final SynchronousQueue<String> QUEUE = new SynchronousQueue<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            put();
            System.out.println("Escreveu na fila");
        };

        Runnable r2 = () -> {
            String msg = take();
            System.out.println("Pega na fila: " + msg);
        };

        executor.execute(r1);
        executor.execute(r2);

        executor.shutdown();

    }

    private static void put() {
        try {
            QUEUE.put("Message sending");// Coloca messagem na fila.
//            QUEUE.offer(e, timeout, unit); // Coloca algo na fila tentando em um determinado tempo.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static String take() {
        try {
            return QUEUE.take(); // pega algo da fila
//            return QUEUE.poll(timeout, unit); // Define um limite de tempo para pegar algo da fila.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Erro";
        }
    }

}
