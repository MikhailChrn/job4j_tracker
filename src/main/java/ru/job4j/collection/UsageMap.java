package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        Map<String, String> names = new HashMap<>();
        names.put("mikhail@mail.ru", "Mikhail Cherneyev");
        names.put("greta@mail.sw", "Igor Gretovich");
        names.put("tutta@mail.com", "Daniel Tittovich");
        names.put("greta@mail.sw", "Greta Thunberg");
        names.put("tutta@mail.com", "Tutta Larson");
        for (String key : names.keySet()) {
            String value = names.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
