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

        sleep();
        sleep();

    }

    private static CompletableFuture<String> process() {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return "Retorno completable";
        });
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
