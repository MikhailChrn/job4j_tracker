package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String dep : deps) {
            StringBuilder buf = new StringBuilder();
            String[] elements = dep.split("/");
            for (String element : elements) {
                buf.append(element + "/");
                tmp.add(removeLastChar(buf.toString()));
            }
        }
        return new ArrayList<>(tmp);
    }

    private static String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }

    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs, new AscDescComp());
    }

   public static void sortDesc(List<String> orgs) {
        Collections.sort(orgs, new DepDescComp());
    }
}
