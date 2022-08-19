package com.example.cyclicbarrier;

import java.util.concurrent.*;

public class CyclicBarrier3 {

    private static BlockingQueue<Double> result = new LinkedBlockingQueue<>();

    private static ExecutorService executor;

    private static Runnable r1;
    private static Runnable r2;
    private static Runnable r3;

    public static void main(String[] args) {

        Runnable summarization = () -> {
            System.out.println("Somando tudo");

            double resultFinnaly = 0;
            resultFinnaly += result.poll();
            resultFinnaly += result.poll();
            resultFinnaly += result.poll();

            System.out.println("Processamento finalizado. Resultado final: " + resultFinnaly);

            System.out.println("-----------------------------------");
            restart();
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, summarization);

        executor = Executors.newFixedThreadPool(3);

        r1 = () -> {
            result.add(432d * 3d);
            await(cyclicBarrier);
            System.out.println("Terminei o processamento.");
        };
        r2 = () -> {
            result.add(Math.pow(3d, 14d));
            await(cyclicBarrier);
            System.out.println("Terminei o processamento.");
        };
        r3 = () -> {
            result.add(45d * 127d / 12d);
            await(cyclicBarrier);
            System.out.println("Terminei o processamento.");
        };

        restart();

//        executor.shutdown();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static void restart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);
    }

}
