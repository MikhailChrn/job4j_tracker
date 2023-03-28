package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        int sizeResult = 0;
        Item[] result = new Item[size];
        for (int index = 0; index < result.length; index++) {
            if (items[index] != null) {
                result[sizeResult] = items[index];
                sizeResult++;
            }
        }
        result = Arrays.copyOf(result, sizeResult);
        return result;
    }

    public Item[] findByName(String key) {
        //получение списка по имени
        int sizeResult = 0;
        Item[] result = new Item[size];
        for (int index = 0; index < result.length; index++) {
            if (key.equals(items[index].getName())) {
                result[sizeResult] = items[index];
                sizeResult++;
            }
        }
        result = Arrays.copyOf(result, sizeResult);
        return result;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }
}
