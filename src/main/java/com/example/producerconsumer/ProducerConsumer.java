package com.example.producerconsumer;

import java.util.Random;

public class ProducerConsumer {

    public static void main(String[] args) {

        Thread producer = new Thread(() -> {
            while (true) {

            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {

            }
        });

        producer.start();
        consumer.start();
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
