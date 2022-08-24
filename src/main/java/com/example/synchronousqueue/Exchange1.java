package com.example.synchronousqueue;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exchange1 {

    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + " Enviando messagem");
            String msg = "Enviando messagem";
            String returnMessage = exchange(msg);
            System.out.println(nameThread + " - " + returnMessage);
        };

        Runnable r2 = () -> {
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + " Obrigado");
            String msg = "Obrigado";
            String returnMessage = exchange(msg);
            System.out.println(nameThread + " - " + returnMessage);
        };

        executor.execute(r1);
        executor.execute(r2);

        executor.shutdown();

    }

    private static String exchange(String msg) {
        try {
            return EXCHANGER.exchange(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Exceção";
        }
    }

}
