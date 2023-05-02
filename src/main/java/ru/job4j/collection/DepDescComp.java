package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String leftElement = left.split("/")[0];
        String rightElement = right.split("/")[0];
        if ((!leftElement.equals(rightElement))) {
            AscDescComp ascDescComp = new AscDescComp();
            return ascDescComp.compare(rightElement, leftElement);
        } else {
            AscDescComp ascDescComp = new AscDescComp();
            int begSubStr = leftElement.length();
            return ascDescComp.compare(left.substring(begSubStr, left.length()),
                                    right.substring(begSubStr, right.length()));
        }
    }
}
