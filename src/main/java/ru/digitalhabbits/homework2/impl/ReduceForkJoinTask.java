package ru.digitalhabbits.homework2.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class ReduceForkJoinTask extends RecursiveTask<Map<Character, Long>> {

    private final List<Map<Character, Long>> input;

    public ReduceForkJoinTask(List<Map<Character, Long>> input) {
        this.input = input;
    }

    @Override
    protected Map<Character, Long> compute() {
        if (input.size() == 2) {
            return merge(input.get(0), input.get(1));
        } else if (input.size() == 1) {
            return input.get(0);
        } else if (input.size() == 0) {
            return new HashMap<>();
        } else {
            int mid = input.size() / 2;
            ReduceForkJoinTask task1 = new ReduceForkJoinTask(input.subList(0, mid));
            ReduceForkJoinTask task2 = new ReduceForkJoinTask(input.subList(mid, input.size()));

            task1.fork().join();
            task2.fork().join();

            return merge(task1.compute(), task2.compute());
        }


    }

    private Map<Character, Long> merge(Map<Character, Long> left, Map<Character, Long> right) {
        Map<Character, Long> map = new HashMap<>(left);
        for (Map.Entry<Character, Long> entry : right.entrySet()) {
            if (map.containsKey(entry.getKey())) {
                map.merge(entry.getKey(), entry.getValue(), Long::sum);
            } else {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }
}

