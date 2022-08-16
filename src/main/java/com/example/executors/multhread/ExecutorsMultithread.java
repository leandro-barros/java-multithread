package com.example.executors.multhread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsMultithread {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

    }

    public static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            String nameThread = Thread.currentThread().getName();
            int number = new Random().nextInt(1000);
            return "(Nome Thread: " + nameThread + ", número Random: " + number + ")";
        }
    }
}
