package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String leftElement = left.split("/")[0];
        String rightElement = right.split("/")[0];
        int result = rightElement.compareTo(leftElement);
        return result != 0
                ? rightElement.compareTo(leftElement)
                : left.substring(leftElement.length(), left.length())
                .compareTo(right.substring(leftElement.length(), right.length()));
    }
}

