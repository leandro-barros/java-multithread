package com.example.producerconsumer;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 3) Usando as ferramentas certas em comparação com o exemplo 1 e 2
 */
public class ProducerConsumer3 {

    private static final BlockingQueue<Integer> QUEUE = new LinkedBlockingDeque<>(5);

    public static void main(String[] args) {

        Runnable producer = () -> {
            simulateProcessing();

            System.out.println("Produzindo");
            int number = new Random().nextInt(10000);
            try {
                QUEUE.put(number);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        Runnable consumer = () -> {
            simulateProcessing();
            System.out.println("Consumindo");

            try {
                QUEUE.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.scheduleWithFixedDelay(producer, 0, 10, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(consumer, 0, 10, TimeUnit.MILLISECONDS);
    }

    private static final void simulateProcessing() {
        int time = new Random().nextInt(10);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
