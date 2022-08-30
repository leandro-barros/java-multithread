package com.example.streamparallel;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class StreamParallelExampe {

    public static void main(String[] args) {
        Instant start = Instant.now();

        Map<Double, Double> map = new ConcurrentHashMap<>();
        IntStream.range(1, 10000000)
//                .parallel()
                .mapToDouble(number -> Math.pow(number, 5))
                .filter(number -> number % 2 == 0)
                .forEach(number -> map.put(number, Math.pow(number, 5)));

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}
