package com.example.cyclicbarrier;

import java.util.concurrent.*;

public class CyclicBarrier2 {

    private static BlockingQueue<Double> result = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Runnable finnaly = () -> {
            System.out.println("Somando tudo");

            double resultFinnaly = 0;
            resultFinnaly += result.poll();
            resultFinnaly += result.poll();
            resultFinnaly += result.poll();

            System.out.println("Processamento finalizado. Resultado final: " + resultFinnaly);
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, finnaly);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable r1 = () -> {
            result.add(432d * 3d);
            await(cyclicBarrier);
            System.out.println("Terminei o processamento.");
        };
        Runnable r2 = () -> {
            result.add(Math.pow(3d, 14d));
            await(cyclicBarrier);
            System.out.println("Terminei o processamento.");
        };
        Runnable r3 = () -> {
            result.add(45d * 127d / 12d);
            await(cyclicBarrier);
            System.out.println("Terminei o processamento.");
        };

        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);

        executor.shutdown();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
