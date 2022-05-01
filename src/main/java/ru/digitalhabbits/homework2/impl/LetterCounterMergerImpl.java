package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

public class LetterCounterMergerImpl implements LetterCountMerger {

    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        Map<Character, Long> res = new HashMap<>();
        first.forEach((key, val) -> res.merge(key, val, Long::sum));
        return res;
    }
}
