package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;

public class StartUI {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    public static void main(String[] args) {
        Item item = new Item("Obj1");
        System.out.println(item.getCreated().format(formatter));
    }
}
