package ru.job4j.collection;

import java.util.Comparator;

public class AscDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        return left.compareTo(right);
    }
}
