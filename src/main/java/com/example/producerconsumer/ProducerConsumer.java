package com.example.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ProducerConsumer {

    private static final List<Integer> INTEGER_LIST = new ArrayList<>(5);
    private static boolean producing = true;
    private static boolean consuming = true;

    public static void main(String[] args) {

        Thread producer = new Thread(() -> {
            while (true) {
                simulateProcessing();

                if (producing) {
                    int number = new Random().nextInt(10000);
                    INTEGER_LIST.add(number);

                    if (INTEGER_LIST.size() == 5) {
                        producing = false;
                    }

                    if (INTEGER_LIST.size() == 1) {
                        consuming = true;
                    }
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                simulateProcessing();

                if (consuming) {
                    Optional<Integer> number = INTEGER_LIST.stream().findFirst();
                    number.ifPresent(n -> {
                        INTEGER_LIST.remove(n);
                    });

                    if (INTEGER_LIST.size() == 0) {
                        consuming = false;
                    }

                    if (INTEGER_LIST.size() == 4) {
                        producing = true;
                    }
                }
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
