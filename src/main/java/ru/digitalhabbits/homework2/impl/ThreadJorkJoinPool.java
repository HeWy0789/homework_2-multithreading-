package ru.digitalhabbits.homework2.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class ThreadJorkJoinPool extends RecursiveTask<Map<Character, Long>> {

    private final String line;

    public ThreadJorkJoinPool(String line) {
        this.line = line;
    }

    @Override
    protected Map<Character, Long> compute() {
        Map<Character, Long> resultMap = new HashMap<>();
        char[] chars = line.toCharArray();

        for (char symbol : chars) {
            if (resultMap.containsKey(symbol)) {
                resultMap.put(symbol, resultMap.get(symbol) + 1);
            } else {
                resultMap.put(symbol, 1L);
            }
        }
        return resultMap;
    }
}
