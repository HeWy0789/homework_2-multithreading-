package ru.digitalhabbits.homework2.impl;

import lombok.RequiredArgsConstructor;
import ru.digitalhabbits.homework2.FileLetterCounter;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

//todo Make your impl
@RequiredArgsConstructor
public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReaderImpl fileReader;

    @Override
    public Map<Character, Long> count(File input) {

        ForkJoinPool pool = new ForkJoinPool();

        List<Map<Character, Long>> list = fileReader.readLines(input)
                .filter(it -> !it.isEmpty())
                .map(it -> pool.invoke(new ThreadJorkJoinPool(it)))
                .collect(Collectors.toList());

        return pool.invoke(new ReduceForkJoinTask(list));
    }
}
