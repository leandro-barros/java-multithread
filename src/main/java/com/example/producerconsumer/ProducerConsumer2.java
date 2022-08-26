package com.example.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * 2) Região critica e exclusão mútua.
 */
public class ProducerConsumer2 {

    private static final List<Integer> INTEGER_LIST = new ArrayList<>(5);
    private static boolean producing = true;
    private static boolean consuming = true;

    public static void main(String[] args) {

        Thread producer = new Thread(() -> {
            try {
                while (true) {
                    simulateProcessing();

                    if (producing) {
                        System.out.println("Produzindo");
                        int number = new Random().nextInt(10000);
                        INTEGER_LIST.add(number);

                        if (INTEGER_LIST.size() == 5) {
                            System.out.println("Pausando produtor");
                            producing = false;
                        }

                        if (INTEGER_LIST.size() == 1) {
                            System.out.println("Iniciando consumidor");
                            consuming = true;
                        }
                    } else {
                        System.out.println("-------- Produtor dormindo!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    simulateProcessing();

                    if (consuming) {
                        System.out.println("Consumindo");
                        Optional<Integer> number = INTEGER_LIST.stream().findFirst();
                        number.ifPresent(n -> {
                            INTEGER_LIST.remove(n);
                        });

                        if (INTEGER_LIST.size() == 0) {
                            System.out.println("Pausando consumidor");
                            consuming = false;
                        }

                        if (INTEGER_LIST.size() == 4) {
                            System.out.println("Iniciando produtor");
                            producing = true;
                        }
                    } else {
                        System.out.println("******** Consumidor dormindo!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
