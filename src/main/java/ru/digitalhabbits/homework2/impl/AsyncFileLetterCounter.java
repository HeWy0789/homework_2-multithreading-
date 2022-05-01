package ru.digitalhabbits.homework2.impl;

import lombok.RequiredArgsConstructor;
import ru.digitalhabbits.homework2.FileLetterCounter;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

//todo Make your impl
@RequiredArgsConstructor
public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReaderImpl fileReader;

    @Override
    public Map<Character, Long> count(File input) {

        ForkJoinPool pool = new ForkJoinPool();

        return fileReader.readLines(input)
                .map(it -> pool.invoke(new ThreadJorkJoinPool(it))).reduce((map1, map2) -> {
                    map2.forEach((key, value) -> map1.merge(key, value, Long::sum));
                    return map1;
                })
                .get();
    }
}
