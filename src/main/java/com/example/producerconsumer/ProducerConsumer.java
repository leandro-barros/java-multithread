package com.example.producerconsumer;

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

}
