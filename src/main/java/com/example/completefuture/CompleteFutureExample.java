package com.example.completefuture;

import java.util.concurrent.CompletableFuture;

public class CompleteFutureExample {

    public static void main(String[] args) {



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
