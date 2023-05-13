package ru.job4j.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapLambdaUsage {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Shoes", 200);
        map.put("Shirt", 200);
        map.put("Pants", 200);

        BiFunction<Integer, Integer, Integer> function = (oldValue, newValue) -> oldValue - newValue;
        int newPrice = map.merge("Shoes", 50, function);
        int newNewPrice = map.merge("Trousers", 50, function);
        System.out.println("New price: " + newPrice);

        map.forEach((key, value) -> System.out.println("Key: " + key + ", value: " + value));
    }
}
