package com.example.completefuture;

import java.util.concurrent.CompletableFuture;

public class CompleteFutureExample {

    public static void main(String[] args) {

        CompletableFuture<String> process = process();

        CompletableFuture<String> thenApply =
                process.thenApply(s -> s + " Imprime thenApply");

        CompletableFuture<Void> thenAccept =
                thenApply.thenAccept(s -> System.out.println(s));

        System.out.println("Final execução");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static CompletableFuture<String> process() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Retorno completable";
        });
    }
}
